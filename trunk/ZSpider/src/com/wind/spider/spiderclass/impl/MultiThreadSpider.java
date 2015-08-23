package com.wind.spider.spiderclass.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.wind.spider.core.analyze.FiltersExecutor;
import com.wind.spider.core.data.SinglePageCrawled;
import com.wind.spider.core.data.UrlToUrl;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.spiderclass.Spider;

/**
 * 多线程爬虫<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 * 
 */
public class MultiThreadSpider extends Spider
{
	private AtomicInteger activeThread = new AtomicInteger(0); // 活跃的线程数量
	private int recordIden; // 数据Id
	private int threadNum; // 线程数配置

	/**
	 * 爬取
	 * 
	 * @throws InterruptedException
	 */
	@Override
	public boolean climb(List<VisitURL> seeds, Map<String, VisitURL> crawRules)
			throws IllegalArgumentException
	{
		// TODO Auto-generated method stub
		if (seeds.isEmpty())
		{
			throw new IllegalArgumentException("seed url can't be empty");
		}
		super.init(seeds);
		getCrawlContentDb().OpenDb(); // 获取数据存储器
		getCrawlPathDb().OpenDb(); // 获取抓取路径存储器
		CrawlThreadJob crawlThreadJob = new CrawlThreadJob(crawRules);
		List<Thread> threadList = new ArrayList<Thread>(threadNum);
		for (int i = 0; i < threadNum; i++)
		{
			Thread t = new Thread(crawlThreadJob);
			t.start();
			threadList.add(t);
		}
		while (threadList.size() > 0)
		{
			Thread child = (Thread) threadList.remove(0);
			try
			{
				child.join(); // 等待子线程执行完
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		crawlContentDb.CloseDb();
		crawlPathDb.CloseDb();
		return true;
	}

	/**
	 * 爬取
	 */
	protected boolean climb(Map<String, VisitURL> crawRules)
	{
		VisitURL currVisitURL = null;
		try
		{
			while ((currVisitURL = dequeueURL()) != null)
			{
				String pageText = pageTextGetter.doGainPageCode(currVisitURL); // 获取网页源码
				if ((pageText.equals("") || pageText == null)
						&& skipQueue != null) // 保存跳过的ＵＲＬ
				{
					skipQueue.add(currVisitURL);
				}
				Map<String, List<String>> crawlContentByPage = FiltersExecutor
						.ObtainCrawlStuff(pageText,
								currVisitURL.getContentFilters()); // 在页面抓取内容
				if (!crawlContentByPage.isEmpty())
				{
					String recordTime = DateFormat.getDateTimeInstance()
							.format(new Date());
					crawlContentDb.insert(new SinglePageCrawled(recordIden++,
							recordTime, currVisitURL.getUrl(),
							crawlContentByPage)); // 抓取数据的入库
				}
				int currentDepth = currVisitURL.getDepth(); // 当前抓取深度
				if (currentDepth >= 0 && currentDepth != 1)
				{ // 排除负数和1（1表示深度为当前页）
					Map<String, List<String>> crawlUrlsByPage = FiltersExecutor
							.ObtainCrawlStuff(pageText,
									currVisitURL.getUrlFilters()); // 在页面抓取URL组
					if (!crawlUrlsByPage.isEmpty())
					{
						String crawlUrlTime = DateFormat.getDateTimeInstance()
								.format(new Date());
						crawlPathDb.insert(new UrlToUrl(crawlUrlTime,
								currVisitURL.getUrl(), crawlUrlsByPage)); // 抓取路径元数据入库
					}
					for (String urlFilterName : crawlUrlsByPage.keySet())
					{
						List<String> crawlUrlsByFilter = crawlUrlsByPage
								.get(urlFilterName);
						VisitURL crawlRules = crawRules.get(urlFilterName);
						if (currentDepth > 1)
						{ // 大于1，表示设置了深度抓取控制
							crawlRules.setDepth(--currentDepth);
						} else
						{ // 等于0,表示默认,一直抓取，直到无法过滤得到URL
							crawlRules.setDepth(0);
						}
						for (String crawUrl : crawlUrlsByFilter)
						{
							crawlRules.setUrl(crawUrl);
							enqueueURL(new VisitURL(crawlRules));
						}
					}
				}
				activeThread.decrementAndGet();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 从待访问队列中取出分析的URL地址，同时把它放入已访问队列
	 * 
	 * @return
	 * @throws Exception
	 */
	private synchronized VisitURL dequeueURL() throws Exception
	{
		while (true)
		{
			if (!unAccessQueue.isQueueEmpty())
			{
				VisitURL visitURL = (VisitURL) unAccessQueue.deQueue();
				if (!accessedQueue.contains(visitURL))
				{
					activeThread.incrementAndGet();
					accessedQueue.add(visitURL);
					return visitURL;
				}
			} else
			{
				if (activeThread.get() > 0) // 如果仍然有其他线程活跃
				{
					wait();
				} else
				{ // 如果其他线程都在等待，则通知所有等待线程集体退出
					notifyAll();
					return null;
				}
			}
		}
	}

	/**
	 * 把新发现的ＵＲＬ放入待访问队列中
	 * 
	 * @param visitURL
	 */
	private synchronized void enqueueURL(VisitURL visitURL)
	{
		if (!accessedQueue.contains(visitURL))
		{
			unAccessQueue.add(visitURL);
			notifyAll();
		}
	}

	/**
	 * 线程任务
	 */
	class CrawlThreadJob implements Runnable
	{
		private Map<String, VisitURL> unCrawUrl; // 规则集

		public CrawlThreadJob(Map<String, VisitURL> unCrawUrl) {
			this.unCrawUrl = unCrawUrl;
		}

		public void run()
		{
			climb(unCrawUrl);
		}

		public Map<String, VisitURL> getUnCrawUrl()
		{
			return unCrawUrl;
		}

		public void setUnCrawUrl(Map<String, VisitURL> unCrawUrl)
		{
			this.unCrawUrl = unCrawUrl;
		}
	}

	public int getThreadNum()
	{
		return threadNum;
	}

	public void setThreadNum(int threadNum)
	{
		this.threadNum = threadNum;
	}
}
