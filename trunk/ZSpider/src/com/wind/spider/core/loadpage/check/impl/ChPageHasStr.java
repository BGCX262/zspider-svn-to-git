package com.wind.spider.core.loadpage.check.impl;

import com.wind.spider.core.loadpage.check.CheckPage;

/**
 * 简单检查网页源码（是否含有指定字符串）<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-11-07
 * 
 */
public class ChPageHasStr extends CheckPage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String checkStr; // 检查字符串

	public boolean isError(String pageText)
	{
		if (pageText.indexOf(checkStr) == -1)
		{
			return true;
		}
		return false;
	}

	public String getCheckStr()
	{
		return checkStr;
	}

	public void setCheckStr(String checkStr)
	{
		this.checkStr = checkStr;
	}
}
