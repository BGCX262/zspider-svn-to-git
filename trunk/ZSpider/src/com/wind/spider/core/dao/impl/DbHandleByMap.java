package com.wind.spider.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.wind.spider.core.dao.DbHandle;
import com.wind.spider.core.data.SinglePageCrawled;

/**
 * 内存存储器(保存抓取的内容)
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-03
 */
public class DbHandleByMap implements DbHandle
{
	private List<SinglePageCrawled> crawlContent;

	@Override
	public void OpenDb()
	{
		// TODO Auto-generated method stub
		crawlContent = new ArrayList<SinglePageCrawled>();
	}

	@Override
	public synchronized void insert(Object object)
	{
		// TODO Auto-generated method stub
		if (crawlContent != null)
		{
			crawlContent.add((SinglePageCrawled) object);
		}
	}

	@Override
	public boolean CloseDb()
	{
		// TODO Auto-generated method stub
		return true;
	}
}
