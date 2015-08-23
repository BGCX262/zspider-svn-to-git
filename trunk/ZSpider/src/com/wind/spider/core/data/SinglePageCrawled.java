package com.wind.spider.core.data;

import java.util.List;
import java.util.Map;

/**
 * 单页单过滤器抓取的数据记录
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-16
 */
public class SinglePageCrawled
{
	private int id; // id
	private String nowTime; // 时间
	private String url; // 网址
	private Map<String, List<String>> crawlContentByPage; // 单页抓取的内容

	public SinglePageCrawled(int id, String nowTime, String url,
			Map<String, List<String>> crawlContentByPage) {
		super();
		this.id = id;
		this.nowTime = nowTime;
		this.url = url;
		this.crawlContentByPage = crawlContentByPage;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Map<String, List<String>> getCrawlContentByPage()
	{
		return crawlContentByPage;
	}

	public void setCrawlContentByPage(
			Map<String, List<String>> crawlContentByPage)
	{
		this.crawlContentByPage = crawlContentByPage;
	}

	public String getNowTime()
	{
		return nowTime;
	}

	public void setNowTime(String nowTime)
	{
		this.nowTime = nowTime;
	}
}
