package com.zyh.spring.utils;


import java.text.ParseException;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	public static String dateToString(Date d) {
		String dateStr = TimeUtils.format(d, "yyyy-MM-dd HH:mm:ss");
		return dateStr;
	}
	
	public static String dateToString(Date d,String format) {
		String dateStr = TimeUtils.format(d, format);
		return dateStr;
	}

	public static Date stringToDate(String s) {
		Date date=null;
		try {
			date = TimeUtils.parseDate(s, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {			
			logger.error(e.getMessage());	
		}
		return date;

	}
	
	public static Date stringToDate(String s,String format) {
		Date date=null;
		try {
			date = TimeUtils.parseDate(s, format);
		} catch (ParseException e) {			
			logger.error(e.getMessage());
		}
		return date;

	}
	
	public static String format(Date date,String pattern) {
		String dateStr = TimeUtils.format(date, pattern);
		return dateStr;

	}
	
	public static Date parse(String date,String pattern) {
		Date dateStr = null;
		try {
			dateStr = TimeUtils.parseDate(date, pattern);
		} catch (ParseException e) {
			logger.error("---日期转换异常:{}", e);
		}
		return dateStr;
	}
	
}
