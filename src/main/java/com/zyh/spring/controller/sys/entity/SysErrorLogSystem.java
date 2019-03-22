package com.zyh.spring.controller.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_ERROR_LOG_SYSTEM")
public class SysErrorLogSystem {

	@Id
	@Column(length = 19, name = "LOG_ID")
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 登录用户名
	 */
	@Column(length = 30, name = "LOGIN_NAME")
	private String loginName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/**
	 * 异常来源（0：中太APP页面异常    1： 中太内部接口异常）
	 */
	@Column(length = 2, name = "ERROR_SOURCE")
	private String errorSource;

	public String getErrorSource() {
		return errorSource;
	}

	public void setErrorSource(String errorSource) {
		this.errorSource = errorSource;
	}
	/**
	 * 错误代码
	 */
	@Column(length = 30, name = "ERROR_CODE")
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * 错误信息
	 */
	@Column(length = 800, name = "ERROR_MSG")
	private String errorMsg;
	
	/**
	 * 请求URL
	 */
	@Column(length = 300, name = "REQUEST_URL")
	private String requestUrl;
	
	/**
	 * 调用方法
	 */
	@Column(length = 100, name = "METHOD_HANDLER")
	private String methodHandler;
	
	/**
	 * 接口交易号
	 */
	@Column(length = 10, name = "TRANS_CODE")
	private String transCode;
	
	/**
	 * 请求参数信息
	 */
	@Column(length = 4000, name = "REQUEST_MSG")
	private String requestMsg;
	
	/**
	 * 返回的响应信息
	 */
	@Column(length = 4000, name = "RESPONSE_MSG")
	private String responseMsg;
	
	/**
	 * 服务器IP
	 */
	@Column(length = 30, name = "SERVER_IP")
	private String serverIp;
	
	/**
	 * 日志记录时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	/**
	 * 扩展字段1  日志类型（0： 正常日志    1：异常日志）
	 */
	@Column(length = 500, name = "EXT1")
	private String ext1;
	
	/**
	 * 扩展字段2
	 */
	@Column(length = 4000, name = "EXT2")
	private String ext2;
	
	/**
	 * 扩展字段3 uuid
	 * 
	 */
	@Column(length = 500, name = "EXT3")
	private String ext3;

	/**
	 * 请求来源
	 */
	@Column(length = 2, name = "RESOURCE_TYPE")
	private String resourceType;
	
	@Column(length = 10, name = "ELAPSED_TIME")
	private Long elapsedTime;
	
	public Long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getMethodHandler() {
		return methodHandler;
	}

	public void setMethodHandler(String methodHandler) {
		this.methodHandler = methodHandler;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
}
