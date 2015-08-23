package com.wind.spider.decorate.impl.schedulejob;

import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wind.spider.core.data.VisitURL;
import com.wind.spider.spiderclass.Spider;

/**
 * 定时<br>
 * 
 * @author yanjun.zhou
 * @version 1.000, 2013-1-26
 * 
 */
public class DataCrawlQuartzJob implements Job
{

	@SuppressWarnings("unchecked")
	public void execute(JobExecutionContext context)
			throws JobExecutionException
	{
		List<VisitURL> seeds = (List<VisitURL>) context.getJobDetail()
				.getJobDataMap().get("seeds");
		Map<String, VisitURL> crawRules = (Map<String, VisitURL>) context
				.getJobDetail().getJobDataMap().get("unCrawUrl");
		Spider spider = (Spider) context.getJobDetail().getJobDataMap()
				.get("spider");
		spider.climb(seeds, crawRules);
	}
}
