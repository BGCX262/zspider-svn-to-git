package com.wind.spider.core.data;

import java.util.List;
import java.util.Map;

/**
 * 抓取路径元数据<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-16
 * 
 */
public class UrlToUrl
{
	private String crawlTime; // 抓取url的时间
	private String currentUrl; // 当前页url地址
	private Map<String, List<String>> crawlUrls; // 抓取的url地址

	public UrlToUrl(String crawlTime, String currentUrl,
			Map<String, List<String>> crawlUrls) {
		super();
		this.crawlTime = crawlTime;
		this.currentUrl = currentUrl;
		this.crawlUrls = crawlUrls;
	}

	public String getCrawlTime()
	{
		return crawlTime;
	}

	public void setCrawlTime(String crawlTime)
	{
		this.crawlTime = crawlTime;
	}

	public String getCurrentUrl()
	{
		return currentUrl;
	}

	public void setCurrentUrl(String currentUrl)
	{
		this.currentUrl = currentUrl;
	}

	public Map<String, List<String>> getCrawlUrls()
	{
		return crawlUrls;
	}

	public void setCrawlUrls(Map<String, List<String>> crawlUrls)
	{
		this.crawlUrls = crawlUrls;
	}
}
