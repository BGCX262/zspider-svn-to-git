package com.wind.spider.core.data;

/**
 * 规则定位器<br>
 * 
 * @author  yanjun.zhou
 * @version 1.1, 2013-3-8
 * 
 */
public class RuleLocator 
{
	String rulekey;    //规则标示符
	String url;      //待被规则解析url
	
	public RuleLocator(String ruleKey,String url)
	{
		this.rulekey=ruleKey;
		this.url=url;
	}

	public String getRulekey() 
	{
		return rulekey;
	}

	public void setRulekey(String rulekey) 
	{
		this.rulekey = rulekey;
	}

	public String getUrl() 
	{
		return url;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}
}
