package com.wind.spider.core.loadpage.check;

import java.io.Serializable;

/**
 * 网页源码检查<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-11-07
 * 
 */
public abstract class CheckPage implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param pageText
	 *            被检查网页源码
	 * @return
	 */
	public abstract boolean isError(String pageText); // 是否有错误
}
