package com.luqili.utils.date;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 扩展日期工具类
 * 
 * @author luqili 2016年12月12日
 *
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	/**
	 * 计算两个日期相差
	 * 
	 * @param d1
	 * @param d2
	 * @param unit
	 *            <li>d1 -d2
	 * @return
	 */
	public static long differ(Date d1, Date d2, long unit) {
		long differ = d1.getTime() - d2.getTime();
		return differ / unit;
	}

	/**
	 * 计算两个日期相差的天数
	 * 
	 * @param d1
	 * @param d2
	 *            <li>d1 -d2
	 * @return
	 */
	public static int differDay(Date d1, Date d2) {
		return (int) differ(d1, d2, MILLIS_PER_DAY);
	}

	/**
	 * 计算两个日期相差的月份
	 * 
	 * @param d1
	 * @param d2
	 *            <li>d1 -d2
	 * @return
	 */
	public static int differMonth(Date d1, Date d2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d1);
		int year_1 = calendar.get(Calendar.YEAR);
		int month_1 = calendar.get(Calendar.MONTH);

		calendar.setTime(d2);
		int year_2 = calendar.get(Calendar.YEAR);
		int month_2 = calendar.get(Calendar.MONTH);
		return (year_1 - year_2) * 12 + (month_1 - month_2);
	}

	/**
	 * 计算两个日期相差的年份
	 * 
	 * @param d1
	 * @param d2
	 *            <li>d1 -d2
	 * @return
	 */
	public static int differYear(Date d1, Date d2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d1);
		int year_1 = calendar.get(Calendar.YEAR);

		calendar.setTime(d2);
		int year_2 = calendar.get(Calendar.YEAR);
		return year_1 - year_2;
	}
}
