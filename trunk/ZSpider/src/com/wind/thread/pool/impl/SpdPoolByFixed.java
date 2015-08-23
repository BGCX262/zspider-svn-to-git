package com.wind.thread.pool.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wind.thread.pool.SpdPool;

/**
 * 基于fixed的线程池<br>
 * 
 * @author yanjun.zhou
 */
public class SpdPoolByFixed implements SpdPool
{
	private int thREAD_NUM; // 并发线程数量

	public ExecutorService ObtainExecutorService()
	{
		return Executors.newFixedThreadPool(thREAD_NUM);
	}

	public int getThREAD_NUM()
	{
		return thREAD_NUM;
	}

	public void setThREAD_NUM(int thREAD_NUM)
	{
		this.thREAD_NUM = thREAD_NUM;
	}
}
