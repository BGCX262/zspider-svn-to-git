package com.wind.spider.spiderclass.config;

import com.wind.spider.core.dao.DbHandle;
import com.wind.spider.core.loadpage.PageCodeGetter;
import com.wind.spider.core.queue.SpiderQueue;

/**
 * 組件配置抽象类
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 */
public abstract class ComponentConfig
{
	protected PageCodeGetter pageTextGetter; // 获取html页面内容
	protected SpiderQueue accessedQueue; // 已访问队列
	protected SpiderQueue unAccessQueue; // 未访问队列
	protected SpiderQueue skipQueue; // 跳过URL队列
	protected DbHandle crawlContentDb; // 抓取数据入库器
	protected DbHandle crawlPathDb; // 抓取路径入库器

	public abstract void config() throws Exception;

	public PageCodeGetter getPageTextGetter()
	{
		return pageTextGetter;
	}

	public void setPageTextGetter(PageCodeGetter pageTextGetter)
	{
		this.pageTextGetter = pageTextGetter;
	}

	public SpiderQueue getAccessedQueue()
	{
		return accessedQueue;
	}

	public void setAccessedQueue(SpiderQueue accessedQueue)
	{
		this.accessedQueue = accessedQueue;
	}

	public SpiderQueue getUnAccessQueue()
	{
		return unAccessQueue;
	}

	public void setUnAccessQueue(SpiderQueue unAccessQueue)
	{
		this.unAccessQueue = unAccessQueue;
	}

	public SpiderQueue getSkipQueue()
	{
		return skipQueue;
	}

	public void setSkipQueue(SpiderQueue skipQueue)
	{
		this.skipQueue = skipQueue;
	}

	public DbHandle getCrawlContentDb()
	{
		return crawlContentDb;
	}

	public void setCrawlContentDb(DbHandle crawlContentDb)
	{
		this.crawlContentDb = crawlContentDb;
	}

	public DbHandle getCrawlPathDb()
	{
		return crawlPathDb;
	}

	public void setCrawlPathDb(DbHandle crawlPathDb)
	{
		this.crawlPathDb = crawlPathDb;
	}
}
