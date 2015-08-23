package com.wind.spider.core.loadpage;

import com.wind.spider.core.data.VisitURL;

/**
 * 抓取网页源码接口<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-10-28
 * 
 */
public interface PageCodeGetter
{
	public String doGainPageCode(VisitURL visitURL); // 抓取页面
}
