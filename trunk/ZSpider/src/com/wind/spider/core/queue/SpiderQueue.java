package com.wind.spider.core.queue;

import com.wind.spider.core.data.VisitURL;

/**
 * 队列接口<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-10-25
 * 
 */
public interface SpiderQueue
{
	public void add(VisitURL visitURL); // 入队列

	public void remove(VisitURL visitURL); // 从队列删除

	public boolean isQueueEmpty(); // 判断队列是否为空

	public boolean contains(VisitURL visitURL); // 判断队列是否包含t

	public VisitURL deQueue(); // 出队列
}
