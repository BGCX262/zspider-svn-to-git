package com.wind.spider.core.loadpage.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.wind.spider.core.data.VisitURL;
import com.wind.spider.core.loadpage.PageCodeGetter;

/**
 * 通过URLConnection,Get请求获取html页面内容<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-10-26
 * 
 */
public class PageCGetterByURLConnectionGet implements PageCodeGetter
{
	/**
	 * Get请求获取html页面内容
	 */
	public String doGainPageCode(VisitURL visitURL)
	{
		BufferedReader in = null;
		String pageText = "";
		try
		{
			String urlName = visitURL.getUrl() + "?" + visitURL.getParams();
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet())
			{
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null)
			{
				pageText += "\n" + line;
			}
		} catch (Exception e)
		{
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		return pageText;
	}
}
