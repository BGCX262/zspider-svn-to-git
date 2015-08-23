package com.wind.spider.factory;

import java.io.FileNotFoundException;
import com.sleepycat.je.DatabaseException;
import com.wind.spider.spiderclass.Spider;

/**
 * 爬虫工厂抽象类<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2013-3-29
 * 
 */
public abstract class SpiderFactory
{
	private static Object initLock = new Object();
	private static String className = "com.tongcheng.spider.factory.factoryImpl.FileSystemSpiderFactory";
	private static SpiderFactory defalutSpiderFac;

	/**
	 * @return 默认工厂
	 */
	public static SpiderFactory getInstance()
	{
		if (defalutSpiderFac == null)
		{
			synchronized (initLock)
			{
				if (defalutSpiderFac == null)
				{
					try
					{
						Class c = Class.forName(className);
						defalutSpiderFac = (SpiderFactory) c.newInstance();
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return defalutSpiderFac;
	}

	/**
	 * 生产爬虫
	 * 
	 * @return 爬虫
	 * @throws IllegalArgumentException
	 * @throws DatabaseException
	 * @throws FileNotFoundException
	 */
	public abstract Spider createDefalutSpider();

	public abstract Spider createSpider(String spiderName);
}
