package com.wind.spider.core.data;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import com.wind.spider.core.analyze.filter.Filter;
import com.wind.spider.core.loadpage.check.CheckPage;

/**
 * 抓取URL地址<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-10-26
 * 
 */
public class VisitURL implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String url; // URL地址
	private String params; // 请求参数 （name=value&name=value形式）
	private int depth; // 抓取深度
	private String charset; // 编码格式
	private String requestType; // 请求类型(get/post)
	private CheckPage checkPage; // 网页源码检查
	private Properties properties; // 请求属性
	private List<Filter> contentFilters; // 内容过滤器组
	private List<Filter> urlFilters; // URL过滤器组

	public VisitURL() {
		this.charset = "utf-8"; // 默认采用utf-8编码
		this.requestType = "get"; // 默认get方式请求
	}

	public VisitURL(String url, String params, List<Filter> contentFilters,
			List<Filter> urlFilters, int depth, String charset,
			String requestType, CheckPage checkPage, Properties properties) {
		super();
		this.url = url;
		this.params = params;
		this.contentFilters = contentFilters;
		this.urlFilters = urlFilters;
		this.depth = depth;
		this.charset = charset;
		this.requestType = requestType;
		this.checkPage = checkPage;
		this.properties = properties;
	}

	public VisitURL(VisitURL visitURL) {
		this.url = visitURL.getUrl();
		this.params = visitURL.getParams();
		this.contentFilters = visitURL.getContentFilters();
		this.urlFilters = visitURL.getUrlFilters();
		this.depth = visitURL.getDepth();
		this.charset = visitURL.getCharset();
		this.requestType = visitURL.getRequestType();
		this.checkPage = visitURL.getCheckPage();
		this.properties = visitURL.getProperties();
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getParams()
	{
		return params;
	}

	public void setParams(String params)
	{
		this.params = params;
	}

	public List<Filter> getContentFilters()
	{
		return contentFilters;
	}

	public void setContentFilters(List<Filter> contentFilters)
	{
		this.contentFilters = contentFilters;
	}

	public List<Filter> getUrlFilters()
	{
		return urlFilters;
	}

	public void setUrlFilters(List<Filter> urlFilters)
	{
		this.urlFilters = urlFilters;
	}

	public int getDepth()
	{
		return depth;
	}

	public void setDepth(int depth)
	{
		this.depth = depth;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}

	public String getRequestType()
	{
		return requestType;
	}

	public void setRequestType(String requestType)
	{
		this.requestType = requestType;
	}

	public CheckPage getCheckPage()
	{
		return checkPage;
	}

	public void setCheckPage(CheckPage checkPage)
	{
		this.checkPage = checkPage;
	}

	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}
}
