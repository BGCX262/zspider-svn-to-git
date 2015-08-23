package com.wind.spider.spiderclass;

import java.util.List;
import java.util.Map;
import com.wind.spider.core.dao.DbHandle;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.core.loadpage.PageCodeGetter;
import com.wind.spider.core.queue.SpiderQueue;
import com.wind.spider.spiderclass.config.ComponentConfig;

/**
 * 爬虫抽象类<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 * 
 */
public abstract class Spider
{
	public static String Spider_Single = "0"; // 单线程爬虫;
	public static String Spider_Multi = "1"; // 多线程爬虫;
	protected ComponentConfig componentConfig; // 爬虫组件初始化

	protected PageCodeGetter pageTextGetter; // 获取html页面内容
	protected SpiderQueue accessedQueue; // 已访问队列
	protected SpiderQueue unAccessQueue; // 未访问队列
	protected SpiderQueue skipQueue; // 跳过URL队列
	protected DbHandle crawlContentDb; // 抓取数据入库器
	protected DbHandle crawlPathDb; // 抓取路径入库器

	public Spider() {
		try
		{
			componentConfig.config();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		setAccessedQueue(componentConfig.getAccessedQueue());
		setUnAccessQueue(componentConfig.getUnAccessQueue());
		setSkipQueue(componentConfig.getSkipQueue());
		setCrawlPathDb(componentConfig.getCrawlPathDb());
		setCrawlContentDb(componentConfig.getCrawlContentDb());
		setPageTextGetter(componentConfig.getPageTextGetter());
	}

	/**
	 * 初始化URL种子
	 * 
	 * @param seeds
	 */
	public void init(List<VisitURL> seeds)
	{
		if (seeds != null && !seeds.isEmpty())
		{
			for (VisitURL seed : seeds)
			{
				unAccessQueue.add(seed);
			}
		}
	}

	/**
	 * 爬取抽象类
	 * 
	 * @param seeds
	 * @param unCrawUrl
	 * @return
	 */
	protected abstract boolean climb(Map<String, VisitURL> crawRules);

	public abstract boolean climb(List<VisitURL> seeds,
			Map<String, VisitURL> crawRules);

	public void setPageTextGetter(PageCodeGetter pageTextGetter)
	{
		this.pageTextGetter = pageTextGetter;
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

	public void setComponentConfig(ComponentConfig componentConfig)
	{
		this.componentConfig = componentConfig;
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
}
