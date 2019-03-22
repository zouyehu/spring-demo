package com.zyh.spring.controller.sys.common;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


/**
 * 系统应用程序上下文
 * 
 * @author HU
 * @version 1.0
 */
@Component
public class ApplicationProperties {

	// 交易超时控制时间，后根交易代码，用于定义某个代码超时控制时间
	public static final String REQUEST_TIMEOUT_PREFIX = "trans.request.timeout";
	// 响应错误码前缀，后根具体错误代码，值为该错误码的文字描述
	public static final String RESPONSE_ERROR_MSG_PREFIX = "trans.response.error";
	// 系统参数变量
	private static Properties properties;
	// 当前处理中的交易数量
	private static int currentTransCount = 0;
	// 服务器IP
	private static String serverIp;
	// 服务器名称
	private static String serverName;

	/**
	 * 克隆系统参数到一个新Map对象
	 * 
	 * @return 克隆后的map对象
	 */
	public static Properties clonePropsToMap() {
		return (Properties) properties.clone();
	}

	/**
	 * 获取系统上下文变量
	 * 
	 * @param name 系统配置项名称
	 * 
	 * @return 系统配置项值
	 */
	public static String getProperty(String name) {
		if (properties == null) {
			return null;
		}
		return properties.getProperty(name);
	}

	/**
	 * 应用参数
	 * 
	 * @return 应用参数
	 */
	public static Properties getProperties() {
		if (properties == null) {
			return null;
		}
		return properties;
	}

	/**
	 * 当前系统未完成交易数
	 * 
	 * @return 当前交易数
	 */
	public static int currentTransCount() {
		return currentTransCount;
	}

	/**
	 * 当前交易数增加
	 * 
	 */
	public static void currentTransCountAdd() {
		currentTransCount++;
	}

	/**
	 * 当前交易数减少
	 * 
	 */
	public static void currentTransCountReduction() {
		currentTransCount--;
		if (currentTransCount < 0) {
			currentTransCount = 0;
		}
	}

	/**
	 * 通过错误码获取错误信息
	 * 
	 * @param errorCode
	 * @return
	 */
	public static String getErrorMsg(String errorCode) {
		return properties.getProperty(RESPONSE_ERROR_MSG_PREFIX + "." + errorCode);
	}

	/**
	 * 获取服务器IP
	 * 
	 * @return
	 */
	public static String getServerIp() {
		return serverIp;
	}

	/**
	 * 获取服务器名称
	 * 
	 * @return
	 */
	public static String getServerName() {
		return serverName;
	}

	public static void setServerIp(String serverIp) {
		ApplicationProperties.serverIp = serverIp;
	}

	public static void setServerName(String serverName) {
		ApplicationProperties.serverName = serverName;
	}

	public static boolean hasServerIp() {
		return StringUtils.isNotEmpty(ApplicationProperties.serverIp);
	}

	public static int getCurrentTransCount() {
		return currentTransCount;
	}

	public void setProperties(Properties properties) {
		ApplicationProperties.properties = properties;
	}

}
