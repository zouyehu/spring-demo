package com.zyh.spring.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期转换工具类
 * 
 * @author HU
 *
 */
public class TimeUtils {

	/**
	 * 按照格式将毫秒数转换为String
	 * 
	 * @param millis  毫秒数
	 * @param pattern 格式
	 * @return
	 */
	public static String format(long millis, String pattern) {
		return DateFormatUtils.format(millis, pattern);
	}

	/**
	 * 按照格式及所属地区语言将毫秒数转换为String
	 * 
	 * @param millis  毫秒数
	 * @param pattern 格式
	 * @param locale  所属地区语言
	 * @return
	 */
	public static String format(long millis, String pattern, Locale locale) {
		return DateFormatUtils.format(millis, pattern, locale);
	}

	/**
	 * 按照格式将Date转换为String
	 * 
	 * @param date
	 * @param pattern 格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 按照格式及所属地区语言将Date转换为String
	 * 
	 * @param date
	 * @param pattern 格式
	 * @param locale  所属地区语言
	 * @return
	 */
	public static String format(Date date, String pattern, Locale locale) {
		return DateFormatUtils.format(date, pattern, locale);
	}

	/**
	 * 按照格式将Calendar转换为String
	 * 
	 * @param calendar
	 * @param pattern  格式
	 * @return
	 */
	public static String format(Calendar calendar, String pattern) {
		return DateFormatUtils.format(calendar, pattern);
	}

	/**
	 * 按照格式及所属地区语言将Calendar转换为String
	 * 
	 * @param calendar
	 * @param pattern  格式
	 * @param locale   所属地区语言
	 * @return
	 */
	public static String format(Calendar calendar, String pattern, Locale locale) {
		return DateFormatUtils.format(calendar, pattern, locale);
	}

	/**
	 * 按照格式将String转换为Date
	 * 
	 * @param string
	 * @param pattern 格式
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String string, String... pattern) throws ParseException {
		return DateUtils.parseDate(string, pattern);
	}

	/**
	 * 按照格式将String转换为Calendar
	 * 
	 * @param string
	 * @param pattern 格式
	 * @return
	 * @throws ParseException
	 */
	public static Calendar parseCalendar(String string, String... pattern) throws ParseException {
		return DateUtils.toCalendar(DateUtils.parseDate(string, pattern));
	}

	/**
	 * 将Date转换为Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(Date date) {
		return DateUtils.toCalendar(date);
	}

	/**
	 * 判断两个Date是否为同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 判断两个Calendar是否为同一天
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static boolean isSameDay(Calendar calendar1, Calendar calendar2) {
		return DateUtils.isSameDay(calendar1, calendar2);
	}

	/**
	 * 判断两个Date的时间是否相等
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameInstant(Date date1, Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 判断两个Calendar的时间是否相等
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static boolean isSameInstant(Calendar calendar1, Calendar calendar2) {
		return DateUtils.isSameDay(calendar1, calendar2);
	}

	/**
	 * 为Date增加年数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addYears(Date date, int amount) {
		return DateUtils.addYears(date, amount);
	}

	/**
	 * 为Date增加月份
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonths(Date date, int amount) {
		return DateUtils.addMonths(date, amount);
	}

	/**
	 * 为Date增加天数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDays(Date date, int amount) {
		return DateUtils.addDays(date, amount);
	}

	/**
	 * 为Date增加星期
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addWeeks(Date date, int amount) {
		return DateUtils.addWeeks(date, amount);
	}

	/**
	 * 为Date增加小时
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addHours(Date date, int amount) {
		return DateUtils.addHours(date, amount);
	}

	/**
	 * 为Date增加分钟
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMinutes(Date date, int amount) {
		return DateUtils.addMinutes(date, amount);
	}

	/**
	 * 为Date增加秒数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addSeconds(Date date, int amount) {
		return DateUtils.addSeconds(date, amount);
	}

	/**
	 * 为Date增加毫秒数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return DateUtils.addMilliseconds(date, amount);
	}

	/**
	 * 新建Date，设置年月日，时分秒毫秒默认为0
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date set(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 修改Date的年月日
	 * 
	 * @param date
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date set(Date date, int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, getHour(date), getMinute(date), getSecond(date));
		c.set(Calendar.MILLISECOND, getMillisecond(date));
		return c.getTime();
	}

	/**
	 * 新建Date，设置年月日时分秒，毫秒默认为0
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date set(int year, int month, int day, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minute, second);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 修改Date的年月日时分秒
	 * 
	 * @param date
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date set(Date date, int year, int month, int day, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minute, second);
		c.set(Calendar.MILLISECOND, getMillisecond(date));
		return c.getTime();
	}

	/**
	 * 新建Date，设置年月日时分秒毫秒
	 * 
	 * @param date
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param millisecond
	 * @return
	 */
	public static Date set(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minute, second);
		c.set(Calendar.MILLISECOND, millisecond);
		return c.getTime();
	}

	/**
	 * 设定Date的年份
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date setYear(Date date, int amount) {
		return DateUtils.setYears(date, amount);
	}

	/**
	 * 设定Date的月份
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date setMonth(Date date, int amount) {
		return DateUtils.setMonths(date, amount);
	}

	/**
	 * 设定Date的日期
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date setDay(Date date, int amount) {
		return DateUtils.setDays(date, amount);
	}

	/**
	 * 设定Date的小时
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date setHour(Date date, int amount) {
		return DateUtils.setHours(date, amount);
	}

	/**
	 * 设定Date的分钟
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date setMinute(Date date, int amount) {
		return DateUtils.setMinutes(date, amount);
	}

	/**
	 * 设定Date的秒数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date setSecond(Date date, int amount) {
		return DateUtils.setSeconds(date, amount);
	}

	/**
	 * 设定Date的毫秒数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date setMillisecond(Date date, int amount) {
		return DateUtils.setMilliseconds(date, amount);
	}

	/**
	 * 以field为基本单位四舍五入Date
	 * 
	 * @param date
	 * @param field
	 * @return
	 */
	public static Date round(Date date, int field) {
		return DateUtils.round(date, field);
	}

	/**
	 * 以field为基本单位四舍五入Calendar
	 * 
	 * @param calendar
	 * @param field
	 * @return
	 */
	public static Calendar round(Calendar calendar, int field) {
		return DateUtils.round(calendar, field);
	}

	/**
	 * 以field为基本单位，从高位开始截取Date，未被截取的部分改为最小值<br>
	 * 如field指定为月份，则日期、时、分、秒、毫秒皆改为最小值<br>
	 * 
	 * @param date
	 * @param field
	 * @return
	 */
	public static Date truncate(Date date, int field) {
		return DateUtils.truncate(date, field);
	}

	/**
	 * 以field为基本单位，从高位开始截取Calendar，未被截取的部分改为最小值<br>
	 * 如field指定为月份，则日期、时、分、秒、毫秒皆改为最小值<br>
	 * 
	 * @param calendar
	 * @param field
	 * @return
	 */
	public static Calendar truncate(Calendar calendar, int field) {
		return DateUtils.truncate(calendar, field);
	}

	/**
	 * 以field为基本单位进位Date<br>
	 * 如field指定为月份，日期为8月7日，则进位成9月1日<br>
	 * 
	 * @param date
	 * @param field
	 * @return
	 */
	public static Date ceiling(Date date, int field) {
		return DateUtils.ceiling(date, field);
	}

	/**
	 * 以field为基本单位进位Calendar<br>
	 * 如field指定为月份，日期为8月7日，则进位成9月1日<br>
	 * 
	 * @param calendar
	 * @param field
	 * @return
	 */
	public static Calendar ceiling(Calendar calendar, int field) {
		return DateUtils.ceiling(calendar, field);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Date的天数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7<br>
	 * 
	 * @param date
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInDays(Date date, int fragment) {
		return DateUtils.getFragmentInDays(date, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Date的小时数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7*24=168<br>
	 * 
	 * @param date
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInHours(Date date, int fragment) {
		return DateUtils.getFragmentInHours(date, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Date的分钟数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7*24*60=10080<br>
	 * 
	 * @param date
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInMinutes(Date date, int fragment) {
		return DateUtils.getFragmentInMinutes(date, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Date的秒数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7*24*60*60= 604800<br>
	 * 
	 * @param date
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInSeconds(Date date, int fragment) {
		return DateUtils.getFragmentInSeconds(date, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Date的毫秒数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7*24*60*60*1000=604800000<br>
	 * 
	 * @param date
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInMilliseconds(Date date, int fragment) {
		return DateUtils.getFragmentInMilliseconds(date, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Calendar的天数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7<br>
	 * 
	 * @param calendar
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInDays(Calendar calendar, int fragment) {
		return DateUtils.getFragmentInDays(calendar, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Calendar的小时数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7*24=168<br>
	 * 
	 * @param calendar
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInHours(Calendar calendar, int fragment) {
		return DateUtils.getFragmentInHours(calendar, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Calendar的分钟数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7*24*60=10080<br>
	 * 
	 * @param calendar
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInMinutes(Calendar calendar, int fragment) {
		return DateUtils.getFragmentInMinutes(calendar, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Calendar的秒数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7*24*60*60= 604800<br>
	 * 
	 * @param calendar
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInSeconds(Calendar calendar, int fragment) {
		return DateUtils.getFragmentInSeconds(calendar, fragment);
	}

	/**
	 * 以fragment为基本单位，计算该单位下Calendar的毫秒数<br>
	 * 如fragment指定为月份，日期为8月7日0时整，则返回值为7*24*60*60*1000=604800000<br>
	 * 
	 * @param calendar
	 * @param fragment
	 * @return
	 */
	public static Long getFragmentInMilliseconds(Calendar calendar, int fragment) {
		return DateUtils.getFragmentInMilliseconds(calendar, fragment);
	}

	/**
	 * 获取Date的年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		return toCalendar(date).get(Calendar.YEAR);
	}

	/**
	 * 获取Date的月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		return toCalendar(date).get(Calendar.MONTH);
	}

	/**
	 * 获取Date年内的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		return toCalendar(date).get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取Date月内的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfMonth(Date date) {
		return toCalendar(date).get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获取Date月内的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		return toCalendar(date).get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取Date年内的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		return toCalendar(date).get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取Date周内的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		return toCalendar(date).get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取Date月内的第几个周几
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeekInMonth(Date date) {
		return toCalendar(date).get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 获取Date的小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		return toCalendar(date).get(Calendar.HOUR);
	}

	/**
	 * 获取Date一年内的第几个小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHourOfDay(Date date) {
		return toCalendar(date).get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取Date的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		return toCalendar(date).get(Calendar.MINUTE);
	}

	/**
	 * 获取Date的秒
	 * 
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date) {
		return toCalendar(date).get(Calendar.SECOND);
	}

	/**
	 * 获取Date的毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static int getMillisecond(Date date) {
		return toCalendar(date).get(Calendar.MILLISECOND);
	}

	/**
	 * 计算两个Date之间相差的年份<br>
	 * 以date2-date1计算<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getSpanInYears(Date date1, Date date2) {
		return getSpanInYears(toCalendar(date1), toCalendar(date2));
	}

	/**
	 * 计算两个Date之间相差的月份<br>
	 * 以date2-date1计算<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getSpanInMonths(Date date1, Date date2) {
		return getSpanInMonths(toCalendar(date1), toCalendar(date2));
	}

	/**
	 * 计算两个Date之间相差的天数<br>
	 * 以date2-date1计算<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getSpanInDays(Date date1, Date date2) {
		return getSpanInDays(toCalendar(date1), toCalendar(date2));
	}

	/**
	 * 计算两个Date之间相差的小时数<br>
	 * 以date2-date1计算<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getSpanInHours(Date date1, Date date2) {
		return getSpanInHours(toCalendar(date1), toCalendar(date2));
	}

	/**
	 * 计算两个Date之间相差的分钟数<br>
	 * 以date2-date1计算<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getSpanInMinutes(Date date1, Date date2) {
		return getSpanInMinutes(toCalendar(date1), toCalendar(date2));
	}

	/**
	 * 计算两个Date之间相差的秒数<br>
	 * 以date2-date1计算<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getSpanInSeconds(Date date1, Date date2) {
		return getSpanInSeconds(toCalendar(date1), toCalendar(date2));
	}

	/**
	 * 计算两个Date之间相差的毫秒数<br>
	 * 以date2-date1计算<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getSpanInMilliseconds(Date date1, Date date2) {
		return getSpanInMilliseconds(toCalendar(date1), toCalendar(date2));
	}

	/**
	 * 计算两个Calendar之间相差的年份<br>
	 * 以calendar2-calendar1计算<br>
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static Long getSpanInYears(Calendar calendar1, Calendar calendar2) {
		Long y = (long) (calendar2.get(1) - calendar1.get(1));
		return calendar2.get(2) > calendar1.get(2)
				|| (calendar2.get(2) == calendar1.get(2) && calendar2.get(5) >= calendar1.get(5)) ? y : y - 1;
	}

	/**
	 * 计算两个Calendar之间相差的月份<br>
	 * 以calendar2-calendar1计算<br>
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static Long getSpanInMonths(Calendar calendar1, Calendar calendar2) {
		Long m = (long) ((calendar2.get(1) - calendar1.get(1)) * 12 + (calendar2.get(2) - calendar1.get(2)));
		return calendar2.get(5) >= calendar1.get(5) ? m : m - 1;
	}

	/**
	 * 计算两个Calendar之间相差的天数<br>
	 * 以calendar2-calendar1计算<br>
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static Long getSpanInDays(Calendar calendar1, Calendar calendar2) {
		calendar1.set(11, 0);
		calendar2.set(11, 0);
		return getSpanInHours(calendar1, calendar2) / 24;
	}

	/**
	 * 计算两个Calendar之间相差的小时数<br>
	 * 以calendar2-calendar1计算<br>
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static Long getSpanInHours(Calendar calendar1, Calendar calendar2) {
		calendar1.set(12, 0);
		calendar2.set(12, 0);
		return getSpanInMinutes(calendar1, calendar2) / 60;
	}

	/**
	 * 计算两个Calendar之间相差的分钟数<br>
	 * 以calendar2-calendar1计算<br>
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static Long getSpanInMinutes(Calendar calendar1, Calendar calendar2) {
		calendar1.set(13, 0);
		calendar2.set(13, 0);
		return getSpanInSeconds(calendar1, calendar2) / 60;
	}

	/**
	 * 计算两个Calendar之间相差的秒数<br>
	 * 以calendar2-calendar1计算<br>
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static Long getSpanInSeconds(Calendar calendar1, Calendar calendar2) {
		calendar1.set(14, 0);
		calendar2.set(14, 0);
		return getSpanInMilliseconds(calendar1, calendar2) / 1000;
	}

	/**
	 * 计算两个Calendar之间相差的毫秒数<br>
	 * 以calendar2-calendar1计算<br>
	 * 
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static Long getSpanInMilliseconds(Calendar calendar1, Calendar calendar2) {
		return calendar2.getTimeInMillis() - calendar1.getTimeInMillis();
	}

}
