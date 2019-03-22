package com.zyh.spring.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * 
 * @author HU
 *
 */
public class StringsUtil {

	public static Map<String, String> platformMap = new HashMap<String, String>();

	static {
		platformMap.put("1", "安卓平台");
		platformMap.put("2", "IOS平台");
	}

	public static String getPlatformName(String platformId) {
		return platformMap.get(platformId);
	}

	/**
	 * 字符长度就计算（包裹汉字的长度按3位计算）
	 * 
	 * @param val 要计算长度的字符串
	 * @return 字符串的长度数
	 */

	/***
	 * 判断字符是否是空字符
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s) {
		return null == s || "".equals(s) || "null".equals(s) || "NULL".equals(s);
	}

	public static int validLength(String val) {
		// 去白
		val = val.trim();
		int amount = 0;// 创建汉字数量计数器
		int notChinese = 0;
		for (int i = 0; i < val.length(); i++) {// 遍历字符串每一个字符
			// 使用正则表达式判断字符是否属于汉字编码
			boolean matches = Pattern.matches("^[\u4E00-\u9FA5]{0,}$", "" + val.charAt(i));
			if (matches) {// 如果是汉字
				amount++;// 累加计数器
			} else {
				notChinese++;
			}
		}
		int total = amount * 3 + notChinese;
		return total;
	}

	/**
	 * 判断字符串不是一个空白和null（半角空格会去掉再比较）
	 * 
	 * @param val
	 * @return Boolean
	 */
	public static Boolean isNotBlank(String val) {
		// 如果是null，返回false。表示是空白字符
		if (val == null) {
			return false;
		} else if ("".equals(val.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是一个空白和null（半角空格会去掉再比较）
	 * 
	 * @param val
	 * @return Boolean
	 */
	public static Boolean isBlank(String val) {
		// 如果是null，返回false。表示是空白字符
		if (val == null) {
			return true;
		} else if (val.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
