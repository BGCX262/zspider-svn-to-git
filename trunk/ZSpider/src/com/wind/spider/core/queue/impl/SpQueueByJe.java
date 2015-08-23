package com.wind.spider.core.queue.impl;

import java.io.FileNotFoundException;
import java.util.Map.Entry;
import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;
import com.wind.spider.core.dao.impl.jeOperate;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.core.queue.SpiderQueue;

/**
 * je实现爬虫队列<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-10-26
 * 
 */
public class SpQueueByJe extends jeOperate implements SpiderQueue
{
	@SuppressWarnings("rawtypes")
	private StoredMap pendingUrisDB = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SpQueueByJe(String homeDirectory) throws DatabaseException,
			FileNotFoundException {
		super(homeDirectory);
		EntryBinding keyBinding = new SerialBinding(javaCatelog, String.class);
		EntryBinding valueBinding = new SerialBinding(javaCatelog,
				VisitURL.class);
		pendingUrisDB = new StoredMap(database, keyBinding, valueBinding, true);
	}

	public void add(VisitURL visitURL)
	{
		put(visitURL.getUrl(), visitURL);
	}

	public void remove(VisitURL visitURL)
	{
		delete(visitURL.getUrl());
	}

	public boolean isQueueEmpty()
	{
		return pendingUrisDB.isEmpty();
	}

	public boolean contains(VisitURL visitURL)
	{
		if (get(visitURL.getUrl()) != null)
			return true;
		else
			return false;
	}

	public VisitURL deQueue()
	{
		VisitURL visitURL = null;
		if (!pendingUrisDB.isEmpty())
		{
			@SuppressWarnings("unchecked")
			Entry<String, VisitURL> entry = (Entry<String, VisitURL>) pendingUrisDB
					.entrySet().iterator().next();
			visitURL = entry.getValue();
			delete(entry.getKey());
			System.out.println(visitURL);
		}
		return visitURL;
	}

	@SuppressWarnings("unchecked")
	protected void put(Object key, Object value)
	{
		pendingUrisDB.put(key, value);
	}

	protected Object get(Object key)
	{
		return pendingUrisDB.get(key);
	}

	protected Object delete(Object key)
	{
		return pendingUrisDB.remove(key);
	}
}
