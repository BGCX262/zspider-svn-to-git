package com.wind.spider.spiderclass.config.impl;

import java.io.FileNotFoundException;
import com.sleepycat.je.DatabaseException;
import com.wind.spider.core.dao.impl.DbHandleBydb4o;
import com.wind.spider.core.queue.impl.SpQueueByJe;
import com.wind.spider.spiderclass.config.ComponentConfig;
import com.wind.util.DateUtil;
import com.wind.util.FileUtil;

/**
 * 简单組件配置类
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 */
public class SimpleComConfig extends ComponentConfig
{
	private String crawlDataStorePath; // 数据存储路径

	public void config() throws DatabaseException, FileNotFoundException
	{
		if (isLegal(crawlDataStorePath) == false)
		{
			throw new FileNotFoundException("该路径不存在");
		}
		String spiderWorkPath = receiveSpiderWorkPath(crawlDataStorePath);
		String accessFile = spiderWorkPath + "/" + "Access"; // 创建已抓取目录
		String unAccessFile = spiderWorkPath + "/" + "unAccess"; // 创建未抓取目录
		String skipFile = spiderWorkPath + "/" + "skip"; // 创建放过目录
		String[] paths = new String[] { accessFile, unAccessFile, skipFile };
		for (String path : paths)
		{
			FileUtil.createDirectory(path);
		}
		this.accessedQueue = new SpQueueByJe(paths[0]);
		this.unAccessQueue = new SpQueueByJe(paths[1]);
		this.skipQueue = new SpQueueByJe(paths[2]);
		this.crawlContentDb = new DbHandleBydb4o(crawlDataStorePath
				+ DateUtil.GainDateByNowDate(0) + ".yap");
		this.crawlPathDb = new DbHandleBydb4o(crawlDataStorePath
				+ DateUtil.GainDateByNowDate(0) + "path.yap");
	}

	/**
	 * 检查数据存储路径是否合法
	 * 
	 * @param crawlDataStorePath
	 * @return
	 */
	private boolean isLegal(String crawlDataStorePath)
	{
		if (crawlDataStorePath.indexOf("\\") == -1)
		{
			return false;
		} else
		{
			if (crawlDataStorePath.lastIndexOf("\\") == crawlDataStorePath
					.length() - 1)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取爬虫工作文件路径
	 * 
	 * @param crawlDataStorePath
	 * @return
	 */
	private String receiveSpiderWorkPath(String crawlDataStorePath)
	{
		int lastDeliLocation = crawlDataStorePath.lastIndexOf("\\");
		return crawlDataStorePath.substring(0, lastDeliLocation);
	}
}
