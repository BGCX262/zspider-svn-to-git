package com.wind.spider.core.queue.impl;

import java.util.LinkedList;

import com.wind.spider.core.data.VisitURL;
import com.wind.spider.core.queue.SpiderQueue;

/**
 * 链表实现爬虫队列<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-10-26
 * 
 */
public class SpQueueByLinkList implements SpiderQueue
{
	// 使用链表实现队列
	private LinkedList<VisitURL> unVisitURL = new LinkedList<VisitURL>();

	/**
	 * 添加待访问URL
	 */
	public void add(VisitURL visitURL)
	{
		// TODO Auto-generated method stub
		unVisitURL.add(visitURL);
	}

	/**
	 * 移除待访问URL
	 */
	public void remove(VisitURL visitURL)
	{
		// TODO Auto-generated method stub
		unVisitURL.remove(visitURL);
	}

	/**
	 * 待访问队列是否为空
	 */
	public boolean isQueueEmpty()
	{
		// TODO Auto-generated method stub
		return unVisitURL.isEmpty();
	}

	/**
	 * 出队列
	 */
	public VisitURL deQueue()
	{
		return unVisitURL.removeFirst();
	}

	/**
	 * 待访问队列是否包含该URL
	 */
	public boolean contains(VisitURL visitURL)
	{
		// TODO Auto-generated method stub
		return unVisitURL.contains(visitURL);
	}

}
