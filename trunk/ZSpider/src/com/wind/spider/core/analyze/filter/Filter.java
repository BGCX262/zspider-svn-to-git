package com.wind.spider.core.analyze.filter;

import java.util.List;

/**
 * 过滤器接口<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-10-28
 * 
 */
public interface Filter
{
	public String getFilterName(); // 获取过滤器的名字

	public List<String> doFilter(String content); // 对抓取的内容进行过滤
}
