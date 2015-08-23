package com.wind.spider.decorate;

import java.util.Map;

import com.wind.spider.core.data.VisitURL;
import com.wind.spider.spiderclass.Spider;

/**
 * 爬虫装饰抽象类<br>
 * 
 * @author yanjun.zhou
 * @version 1.000, 2013-4-3
 * 
 */
public abstract class SpiderDec extends Spider
{
	private Spider spider;

	public SpiderDec(Spider spider) {
		this.spider = spider;
	}

	public Spider getSpider()
	{
		return spider;
	}

	protected boolean climb(Map<String, VisitURL> crawRules)
	{
		return true;
	}
}
