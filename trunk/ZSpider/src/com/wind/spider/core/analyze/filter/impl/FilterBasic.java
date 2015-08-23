package com.wind.spider.core.analyze.filter.impl;

import java.io.Serializable;

import com.wind.spider.core.analyze.filter.Filter;

/**
 * 内容过滤器抽象基类<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-11-03
 * 
 */
public abstract class FilterBasic implements Filter, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1182945260321248805L;
	private String filterName; // 过滤器名称

	public String getFilterName() // 返回过滤器名称
	{
		return filterName;
	}

	public void setFilterName(String filterName) // 设置过滤器
	{
		this.filterName = filterName;
	}
}
