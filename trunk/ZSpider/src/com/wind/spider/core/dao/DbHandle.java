package com.wind.spider.core.dao;

/**
 * <br>
 * 抓取数据储存接口
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-03
 * 
 */
public interface DbHandle
{
	public void OpenDb(); // 打开储存器

	public void insert(Object object); // 存入数据

	public boolean CloseDb(); // 关闭储存器
}
