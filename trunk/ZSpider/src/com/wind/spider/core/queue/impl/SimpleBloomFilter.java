package com.wind.spider.core.queue.impl;

import java.util.BitSet;
import com.wind.spider.core.data.VisitURL;
import com.wind.spider.core.queue.SpiderQueue;

/**
 * 简单布隆过滤器实现爬虫队列<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-01
 * 
 */
public class SimpleBloomFilter implements SpiderQueue
{
	private static final int DEFAULT_SIZE = 8 << 24;
	private static final int[] seeds = new int[] { 7, 11, 13, 31, 37, 61, 71,
			97 };
	private BitSet bits = new BitSet(DEFAULT_SIZE);
	private SimpleHash[] func = new SimpleHash[seeds.length];

	public SimpleBloomFilter() {
		for (int i = 0; i < seeds.length; i++)
		{
			func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
		}
	}

	public void add(VisitURL visitURL)
	{
		if (visitURL != null)
		{
			for (SimpleHash f : func)
			{
				bits.set(f.hash(visitURL.getUrl()), true);
			}
		}
	}

	public void remove(VisitURL visitURL)
	{

	}

	public boolean isQueueEmpty()
	{

		return false;
	}

	public boolean contains(VisitURL visitURL)
	{
		String url = visitURL.getUrl();
		if (url == null)
		{
			return false;
		}
		boolean ret = true;
		for (SimpleHash f : func)
		{
			ret = ret && bits.get(f.hash(url));
			if (ret == false)
				break;
		}
		return ret;
	}

	public VisitURL deQueue()
	{

		return null;
	}

	public static class SimpleHash
	{
		private int cap;
		private int seed;

		public SimpleHash(int cap, int seed) {
			this.cap = cap;
			this.seed = seed;
		}

		public int hash(String value)
		{
			int result = 0;
			int len = value.length();
			for (int i = 0; i < len; i++)
			{
				result = seed * result + value.charAt(i);
			}
			return (cap - 1) & result;
		}
	}
}
