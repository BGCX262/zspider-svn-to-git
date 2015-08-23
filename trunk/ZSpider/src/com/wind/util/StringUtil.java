package com.wind.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 */
public class StringUtil
{

	/**
	 * 对字符串解析出需要的字符串组
	 * 
	 * @param content
	 *            待解析字符串
	 * @param regExp
	 *            正则表达式
	 * @return 解析出来的字符串组
	 */
	public static List<String> parse(String content, String regExp, int[] index)
	{
		List<String> result = new ArrayList<String>();
		Matcher m = getMatcher(content, regExp);
		while (m.find())
		{
			int i = 0;
			while (i < index.length)
			{
				result.add(m.group(index[i]));
				i++;
			}
		}
		return result;
	}

	/**
	 * 对字符串解析出需要的字符串
	 * 
	 * @param content
	 *            正则表达式
	 * @param regExp
	 *            正则表达式
	 * @return 解析出来的字符串
	 */
	public static String parse(String content, String regExp, int index)
	{
		String result = "";
		Matcher m = getMatcher(content, regExp);
		if (m.find())
		{
			result = m.group(index);
		}
		return result;
	}

	/**
	 * 判断字符串中是否包含特定的字符串
	 * 
	 * @param content
	 *            待判断字符串
	 * @param regExp
	 *            正则表达式
	 * @return
	 */
	public static boolean contains(String content, String regExp)
	{
		Matcher m = getMatcher(content, regExp);
		return m.find();
	}

	/**
	 * 返回正则对象
	 * 
	 * @param content
	 * @param regExp
	 * @return
	 */
	private static Matcher getMatcher(String content, String regExp)
	{
		content = content.replaceAll("\r|\n", ""); // 去掉回车，换行符
		Pattern p = Pattern.compile(regExp);
		return p.matcher(content);
	}

}
