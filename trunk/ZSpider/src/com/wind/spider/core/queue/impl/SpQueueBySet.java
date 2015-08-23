package com.wind.spider.core.queue.impl;

import java.util.HashSet;
import java.util.Set;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.core.queue.SpiderQueue;

/**
 * Set实现爬虫队列<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-10-26
 * 
 */
public class SpQueueBySet implements SpiderQueue
{
	// 已访问的VisitURL集合
	private Set<VisitURL> visitedUrl = new HashSet<VisitURL>();

	/**
	 * 添加已访问URL
	 */
	public void add(VisitURL visitURL)
	{
		// TODO Auto-generated method stub
		visitedUrl.add(visitURL);
	}

	/**
	 * 移除已访问URL
	 */
	public void remove(VisitURL visitURL)
	{
		// TODO Auto-generated method stub
		visitedUrl.remove(visitedUrl);
	}

	/**
	 * 已访问队列是否为空
	 */
	public boolean isQueueEmpty()
	{
		// TODO Auto-generated method stub
		return visitedUrl.isEmpty();
	}

	/**
	 * 出队列
	 */
	public VisitURL deQueue()
	{
		VisitURL temp = visitedUrl.iterator().next();
		visitedUrl.remove(temp);
		return temp;
	}

	/**
	 * 已访问队列是否包括指定的URL
	 */
	public boolean contains(VisitURL visitURL)
	{
		boolean flag = false;
		if (!visitedUrl.isEmpty())
		{
			for (VisitURL visiturl : visitedUrl)
			{
				if (visiturl.getUrl().equalsIgnoreCase(visitURL.getUrl()))
				{
					flag = true;
				}
			}
		}
		return flag;
	}
}
