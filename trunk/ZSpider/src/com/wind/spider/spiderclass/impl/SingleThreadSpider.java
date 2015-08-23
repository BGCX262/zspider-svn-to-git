package com.wind.spider.spiderclass.impl;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wind.spider.core.analyze.FiltersExecutor;
import com.wind.spider.core.data.SinglePageCrawled;
import com.wind.spider.core.data.UrlToUrl;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.spiderclass.Spider;

/**
 * 单线程爬虫<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 * 
 */
public class SingleThreadSpider extends Spider
{

	@Override
	/**
	 * 抓取
	 */
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
		return climb(crawRules);
	}

	protected boolean climb(Map<String, VisitURL> crawRules)
	{
		int recordIden = 0;
		while (!unAccessQueue.isQueueEmpty()) // 未访问URL队列不为空
		{
			VisitURL visitURL = unAccessQueue.deQueue(); // 从未访问URL队列取出url
			if (visitURL == null)
				continue;
			if (accessedQueue.contains(visitURL))
				continue; // 如果已访问队列有该url，则取下一个
			String pageText = pageTextGetter.doGainPageCode(visitURL); // 获取内容
			if (pageText.equals("") || pageText == null) // 保存跳过的ＵＲＬ
			{
				skipQueue.add(visitURL);
			}
			Map<String, List<String>> crawlContentByPage = FiltersExecutor
					.ObtainCrawlStuff(pageText, visitURL.getContentFilters()); // 在页面抓取内容
			if (!crawlContentByPage.isEmpty())
			{
				String recordTime = DateFormat.getDateTimeInstance().format(
						new Date());
				crawlContentDb.insert(new SinglePageCrawled(recordIden++,
						recordTime, visitURL.getUrl(), crawlContentByPage)); // 抓取数据的入库
			}
			accessedQueue.add(new VisitURL(visitURL)); // 将访问url放入已访问队列

			int currentDepth = visitURL.getDepth(); // 当前抓取深度
			if (currentDepth >= 0 && currentDepth != 1) // 不等于1：表示存在深度抓取
			{
				Map<String, List<String>> crawlUrlsByPage = FiltersExecutor
						.ObtainCrawlStuff(pageText, visitURL.getUrlFilters()); // 在页面抓取url组
				if (!crawlUrlsByPage.isEmpty())
				{
					String crawlUrlTime = DateFormat.getDateTimeInstance()
							.format(new Date());
					crawlPathDb.insert(new UrlToUrl(crawlUrlTime, visitURL
							.getUrl(), crawlUrlsByPage)); // 抓取路径元数据入库
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
					{ // 等于0,表示默认没设置抓取深度控制，一直抓取，直到无法过滤得到url
						crawlRules.setDepth(0);
					}
					for (String crawUrl : crawlUrlsByFilter)
					{
						crawlRules.setUrl(crawUrl);
						unAccessQueue.add(new VisitURL(crawlRules));
					}
				}
			}
		}
		return true;
	}
}
