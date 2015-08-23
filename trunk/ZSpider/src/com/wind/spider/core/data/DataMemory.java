package com.wind.spider.core.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据记录<br>
 * 
 * @author 	yanjun.zhou
 * @version 1.1, 2013-3-8
 * 
 */
public class DataMemory<T> 
{
	private T date;
	private List<RuleLocator> ruleLocators;
	
	public DataMemory()
	{
		ruleLocators=new ArrayList<RuleLocator>();
	}
	
	public T getDate() {
		return date;
	}
	public void setDate(T date) {
		this.date = date;
	}
	public List<RuleLocator> getRuleLocators() {
		return ruleLocators;
	}
	public void setRuleLocator(RuleLocator ruleLocator) {
		this.ruleLocators.add(ruleLocator);
	}
	public RuleLocator getRuleLocator(int index) {
		return this.ruleLocators.get(index);
	}
}
