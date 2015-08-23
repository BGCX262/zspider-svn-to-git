package com.wind.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具<br>
 * 
 * @author yanjun.zhou
 * @version 1.1, 2012-12-8
 */
public class DateUtil
{
	/**
	 * 格式化时间
	 * 
	 * @param FormatStr
	 *            格式化字符串
	 * @param date
	 *            时间
	 * @return
	 */
	public static String Format(String FormatStr, Date date)
	{
		SimpleDateFormat simleDateFormat = new SimpleDateFormat(FormatStr);
		return simleDateFormat.format(date);
	}

	/**
	 * 返回当前时间起未来dates的日期
	 * 
	 * @param dates
	 *            未来的天数
	 * @return
	 */
	public static String GainDateByNowDate(int dates)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, dates);
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
