package com.wind.spider.core.loadpage.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.core.loadpage.PageCodeGetter;
import com.wind.spider.core.loadpage.check.CheckPage;
import com.wind.util.HttpUtil;
import com.wind.util.bean.HttpResponse;

/**
 * 本地下载源码<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-02
 * 
 */
public class PageCGetterByLocalhost implements PageCodeGetter
{
	private int failureTime; // 下载次数阀值
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public PageCGetterByLocalhost(int failureTime) {
		this.failureTime = failureTime;
	}

	/**
	 * 获取网页源码
	 */
	public String doGainPageCode(VisitURL visitURL)
	{
		String pageText = "";
		int index = 0;
		CheckPage checkPage = visitURL.getCheckPage(); // 获取源码检查
		HttpResponse resp = null;
		boolean successSign = true;
		while (successSign)
		{
			index++; // 计数
			try
			{
				resp = HttpUtil.getContent(visitURL.getUrl(),
						visitURL.getParams(), visitURL.getRequestType(),
						visitURL.getCharset(), visitURL.getCharset(),
						visitURL.getProperties(), "", 0);
			} catch (IOException e)
			{
				logger.info("load page failure：" + visitURL.getUrl());
				if (index > failureTime)
					break;
				continue;
			}
			if (resp == null)
			{
				logger.info("load page failure：" + visitURL.getUrl());
				if (index > failureTime)
					break; // 超过下载次数，则跳出
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
		if (index <= failureTime) // 如果小于阀值，表示下载成功，否则失败，并且放过去（即不再重新抓取）
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

	public int getFailureTime()
	{
		return failureTime;
	}

	public void setFailureTime(int failureTime)
	{
		this.failureTime = failureTime;
	}
}
