package com.zyh.spring.controller.sys.common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 交易系统请求对像 交易请求均通过该模型调用和请求交易系统
 * 
 * @author HU
 * @version 1.0
 */
public class Request implements Serializable, Comparable<Request> {
	private static final long serialVersionUID = -5539534363467777475L;
	public static final String STATUS_INIT = "000";
	public static final String STATUS_COMPLETE = "100";
	public static final String TRANS_TYPE_SYNC = "0";
	public static final String TRANS_TYPE_ASYN = "1";

	// 请求流水号
	private String requestNo;
	// 业务员，操作员
	private String username;
	// 客户端设备编号
	private String clientId;
	// 请求客户端IP
	private String clientIp;
	// 事务流水号
	private String transactionNo;
	// 请求交易编号
	private String transCode;
	// 交易类型:0：同步，1：异步
	private String transType;
	// 请求时间
	private Date requestTime;
	// 请求到达时间
	private Date arrivalTime;
	// 请求方appId
	private String appId;
	// 请求方appSecret
	private String appSecret;

	// 请求主体
	private Object requestBody;

	/**
	 * 请求报文原始json
	 */
	private String requestJson;

	private Map<String, String> extension = new HashMap<String, String>();
	// 状态
	private String status;

	private boolean debug;

	private String accessToken;

	/**
	 * 当前httpSession对象，用于向session当中存放值。存放的内容不可过多。
	 */
	private HttpSession httpSession;
	/**
	 * httpServletRespone ，用于添加cookie使用。不可以做其他用处。
	 */
	private HttpServletResponse httpResponse;
	/**
	 * httpServletRespone ，用于添加cookie使用。不可以做其他用处。
	 */
	private HttpServletRequest httpRequest;

	public int compareTo(Request otherRequest) {
		return transactionNo.compareTo(otherRequest.getTransactionNo());
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}

	public String getExtension(String key) {
		return extension.get(key);
	}

	public void putExtension(String key, String value) {
		this.extension.put(key, value);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * 
	 * @param transCode
	 * @param channelCode
	 * @return
	 */
	public static Request createRequestHeader(String transCode, String channelCode) {
		String requestno = Long.toHexString(System.currentTimeMillis()) + String.valueOf(Math.random()).substring(2, 8);

		Request request = new Request();

		request.setRequestNo(requestno);
		request.setTransCode(transCode);
		request.setRequestTime(new Date());
		request.setArrivalTime(new Date());

		request.setTransType(Request.TRANS_TYPE_SYNC);
		request.setStatus(Request.STATUS_INIT);

		return request;
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	/**
	 * 往session当中存放内容，
	 * 
	 * @param key
	 * @param object
	 */
	public void setSessionAttr(String name, Object value) {
		this.httpSession.setAttribute(name, value);
	}

	/**
	 * 从session当中获取值
	 * 
	 * @param name
	 */
	public Object getSessionAttr(String name) {
		if (this.httpSession == null) {
			return null;
		}
		return this.httpSession.getAttribute(name);
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setHttpResponse(HttpServletResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	/**
	 * httpRequest the httpRequest to set
	 * 
	 * @param
	 */
	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public String getCookieValByName(String name) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();

		Cookie[] cookies = this.httpRequest.getCookies();

		if (null != cookies) {

			for (Cookie cookie : cookies) {

				cookieMap.put(cookie.getName(), cookie);

			}

		}
		if (cookieMap.size() > 0 && cookieMap.containsKey(name)) {

			Cookie cookie = (Cookie) cookieMap.get(name);

			return cookie.getValue();

		} else {

			return null;

		}
	}

	/**
	 * 向http servlet response对象当中添加cookie对象
	 * 
	 * @param cookie
	 */
	public void addCookie(Cookie cookie) {
		this.httpResponse.addCookie(cookie);
	}

	public String getMethod() {
		if (this.httpRequest == null) {
			return "";
		}
		return this.httpRequest.getMethod();
	}

	public String getContextPath() {
		if (this.httpRequest == null) {
			return "";
		}
		return this.httpRequest.getContextPath();
	}

	public String getServerIp() {
		if (this.httpRequest == null) {
			return "";
		}
		String ip = this.httpRequest.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = this.httpRequest.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = this.httpRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = this.httpRequest.getRemoteAddr();
		}
		return ip;
	}
}
