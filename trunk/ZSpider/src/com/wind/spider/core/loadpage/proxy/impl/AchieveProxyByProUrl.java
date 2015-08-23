package com.wind.spider.core.loadpage.proxy.impl;

import java.io.IOException;

import com.wind.spider.core.data.HttpProxy;
import com.wind.spider.core.loadpage.proxy.AchieveProxy;
import com.wind.util.HttpUtil;
import com.wind.util.bean.HttpResponse;

/**
 * 通过提供代理网址获取代理ip<br>
 * 
 * @author yanjun.zhou
 * @version 1.000, 2012-12-02
 * 
 */
public class AchieveProxyByProUrl implements AchieveProxy
{
	private String proxysUrl; // 提供代理网址的url地址

	public AchieveProxyByProUrl(String proxysUrl) {
		this.proxysUrl = proxysUrl;
	}

	public HttpProxy doObtainProxy()
	{
		HttpResponse resp = null;
		try
		{
			resp = HttpUtil.getContent(proxysUrl, "", HttpUtil.METHOD_GET,
					"utf-8", "utf-8", null, "", 0);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		String content = "";
		if (resp != null)
		{
			content = resp.getContent();
			content = content.replaceAll("\n\r", "").replace("<br>", "");
		}
		if (content != null && !content.equals(""))
		{
			HttpProxy httpProxy = new HttpProxy();
			httpProxy.setIp(content.substring(0, content.indexOf(":")).trim());
			Integer port = Integer.valueOf(content.substring(
					content.indexOf(":") + 1).trim());
			httpProxy.setPort(port);
			return httpProxy;
		}
		return null;
	}

	public String getProxysUrl()
	{
		return proxysUrl;
	}

	public void setProxysUrl(String proxysUrl)
	{
		this.proxysUrl = proxysUrl;
	}
}
