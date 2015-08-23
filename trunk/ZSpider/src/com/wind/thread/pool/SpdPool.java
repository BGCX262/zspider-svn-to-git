package com.wind.thread.pool;

import java.util.concurrent.ExecutorService;

/**
 * 线程池接口<br>
 * 
 * @author yanjun.zhou
 */
public interface SpdPool
{
	public ExecutorService ObtainExecutorService(); // 获取线程池对象
}
