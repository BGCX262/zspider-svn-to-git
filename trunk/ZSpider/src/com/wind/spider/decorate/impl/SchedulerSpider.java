package com.wind.spider.decorate.impl;

import java.util.List;
import java.util.Map;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.wind.spider.core.data.VisitURL;
import com.wind.spider.decorate.SpiderDec;
import com.wind.spider.decorate.impl.schedulejob.DataCrawlQuartzJob;
import com.wind.spider.spiderclass.Spider;

/**
 * 定时爬虫<br>
 * 
 * @author yanjun.zhou
 * @version 1.000, 2013-4-3
 * 
 */
public class SchedulerSpider extends SpiderDec
{
	private String alarmTime;

	public SchedulerSpider(Spider spider) {
		super(spider);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean climb(List<VisitURL> seeds, Map<String, VisitURL> crawRules)
	{
		SchedulerFactory schedulerFactory = new StdSchedulerFactory(); // 定时器工厂
		JobDetail DataCrawljobDetail = new JobDetail("DataCrawlQuartzJob",
				"jobDetailGroup", DataCrawlQuartzJob.class); // 实例化定时数据抓取job
		DataCrawljobDetail.getJobDataMap().put("seeds", seeds);
		DataCrawljobDetail.getJobDataMap().put("crawRules", crawRules);
		DataCrawljobDetail.getJobDataMap().put("spider", this.getSpider());
		CronTrigger DataCrawlTrigger = new CronTrigger("DataCrawlTrigger",
				"triggerGroup");
		try
		{
			CronExpression cexp = new CronExpression(alarmTime);
			DataCrawlTrigger.setCronExpression(cexp);
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.scheduleJob(DataCrawljobDetail, DataCrawlTrigger);
			scheduler.start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	public String getAlarmTime()
	{
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime)
	{
		this.alarmTime = alarmTime;
	}
}
