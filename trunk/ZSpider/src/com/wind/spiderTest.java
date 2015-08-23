package com.wind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.decorate.impl.SchedulerSpider;
import com.wind.spider.factory.SpiderFactory;
import com.wind.spider.spiderclass.Spider;

public class spiderTest
{
	public static void main(String[] args)
	{
		SpiderFactory spiderFactory = SpiderFactory.getInstance();        //爬虫工厂
		Spider spider = spiderFactory.createDefalutSpider();               //制造默认爬虫

		SchedulerSpider schedulerSpider = new SchedulerSpider(spider);    //保证成定时爬虫
		schedulerSpider.setAlarmTime("0 30 5 * * *");           //设置定时时间

		List<VisitURL> seeds = new ArrayList<VisitURL>();
		Map<String, VisitURL> crawRules = new HashMap<String, VisitURL>();
		spider.climb(seeds, crawRules);          //定制爬取任务

		schedulerSpider.climb(seeds, crawRules);       //爬取数据
	}
}
