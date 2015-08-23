package com.wind.spider.core.analyze;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wind.spider.core.analyze.filter.Filter;

/**
 * 过滤器执行器<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-12
 * 
 */
public class FiltersExecutor
{
	/**
	 * 获取需要抓取部件
	 * 
	 * @param content
	 * @param filters
	 * @return
	 */
	public static Map<String, List<String>> ObtainCrawlStuff(String pageText,
			List<Filter> filters)
	{
		Map<String, List<String>> contents = new HashMap<String, List<String>>();
		if (filters != null && !filters.isEmpty() && pageText != null
				&& !pageText.equals("")) // 内容群组过滤器不为空
		{
			for (Filter filter : filters)
			{
				List<String> crawContent = filter.doFilter(pageText);
				contents.put(filter.getFilterName(), crawContent); // 以过滤器名称，保存抓取的内容
			}
		}
		return contents;
	}
}
