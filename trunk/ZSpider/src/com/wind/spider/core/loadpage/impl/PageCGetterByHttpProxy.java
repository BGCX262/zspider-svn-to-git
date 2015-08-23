package com.wind.spider.core.loadpage.impl;

import java.io.IOException;
import org.apache.log4j.Logger;

import com.wind.spider.core.data.HttpProxy;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.core.loadpage.PageCodeGetter;
import com.wind.spider.core.loadpage.check.CheckPage;
import com.wind.spider.core.loadpage.proxy.AchieveProxy;
import com.wind.util.HttpUtil;
import com.wind.util.bean.HttpResponse;

/**
 * 获取页面源码(设置代理)<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-04
 * 
 */
public class PageCGetterByHttpProxy implements PageCodeGetter
{
	private AchieveProxy obtainProxy; // 获取代理IP
	private int failureTime; // 下载次数阀值
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public PageCGetterByHttpProxy() {
	}

	public PageCGetterByHttpProxy(AchieveProxy obtainProxy, int failureTime) {
		this.obtainProxy = obtainProxy;
		this.failureTime = failureTime;
	}

	public String doGainPageCode(VisitURL visitURL)
	{
		String pageText = "";
		int index = 0;
		CheckPage checkPage = visitURL.getCheckPage(); // 获取源码检查
		HttpResponse resp = null;
		boolean successSign = true;
		while (successSign)
		{
			HttpProxy httpProxy = getHttpProxyUntil(); // 提取代理
			index++; // 下载计数
			try
			{
				resp = HttpUtil.getContent(visitURL.getUrl(),
						visitURL.getParams(), visitURL.getRequestType(),
						visitURL.getCharset(), visitURL.getCharset(),
						visitURL.getProperties(), httpProxy.getIp(),
						httpProxy.getPort());
			} catch (IOException e)
			{
				logger.info("load page failure：" + visitURL.getUrl());
				if (index > failureTime)
					break; // 超过下载次数，则跳出
				continue;
			}
			if (resp == null)
			{
				logger.info("load page failure：" + visitURL.getUrl());
				if (index > failureTime)
					break;
				continue;
			}
			if (resp.getResponseCode() == 200)
			{
				pageText = resp.getContent();
				if (checkPage == null)
				{ // 如果检查器没设置
					successSign = (pageText == null || pageText.equals(""));
				} else
				{
					successSign = (pageText == null || pageText.equals("") || checkPage
							.isError(pageText));
				}
			}
		}
		if (index <= failureTime)
		{
			logger.info("Last load page success! index=" + index + ",page:"
					+ visitURL.getUrl());
		} else
		{
			logger.info("Last load page failure! index=" + index + ",page:"
					+ visitURL.getUrl());
		}
		return pageText;
	}

	/**
	 * 循环寻找Ip地址，直到找到
	 * 
	 * @param service
	 * @param proxysUrl
	 * @return
	 */
	public HttpProxy getHttpProxyUntil()
	{
		HttpProxy httpProxy = null;
		while (httpProxy == null)
		{
			httpProxy = obtainProxy.doObtainProxy(); // 获取新的代理IP地址
		}
		return httpProxy;
	}

	public AchieveProxy getObtainProxy()
	{
		return obtainProxy;
	}

	public void setObtainProxy(AchieveProxy obtainProxy)
	{
		this.obtainProxy = obtainProxy;
	}

	public int getFailureTime()
	{
		return failureTime;
	}

	public void setFailureTime(int failureTime)
	{
		this.failureTime = failureTime;
	}
}
