package com.wind.spider.factory.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wind.spider.factory.SpiderFactory;
import com.wind.spider.spiderclass.Spider;

/**
 * 基于文件<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2013-3-29
 * 
 */
public class FileSystemSpiderFactory extends SpiderFactory
{
	private static final String DEFAULT_CONFIG_PATH = "com/tongcheng/spider_default.xml";
	private static final String DEFAULT_SPIDER_NAME = "multiThreadSpider";
	private ApplicationContext appcontext;

	public FileSystemSpiderFactory() {
		appcontext = new ClassPathXmlApplicationContext(DEFAULT_CONFIG_PATH);
	}

	public FileSystemSpiderFactory(String configFile) {
		appcontext = new ClassPathXmlApplicationContext(configFile);
	}

	public Spider createDefalutSpider()
	{
		return createSpider(DEFAULT_SPIDER_NAME);
	}

	public Spider createSpider(String spiderName)
	{
		return (Spider) appcontext.getBean(spiderName);
	}
}
