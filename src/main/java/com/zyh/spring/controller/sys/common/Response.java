package com.zyh.spring.controller.sys.common;

/**
 * 交易系统响应对像，该模型将返回响应结果给最终用户，包含了响应需要的各种数据 responseNo将对应而且完全相同，transNo（如果有值）将对应
 * 而且完全相同
 * 
 * @author HU
 * @version 1.0
 */
public class Response implements java.io.Serializable {
	private static final long serialVersionUID = 2553809076321136643L;
	public static final String STATUS_INIT = "000";
	/**
	 * 返回结果正常
	 */
	public static final String STATUS_SUCCESS = "100";
	/**
	 * 返回结果异常：有异常出现的时候为该状态
	 */
	public static final String STATUS_ERROR = "999";

	private String transCode;
	// 响应主体
	private Object responseBody;
	// 处理费时（不计算网络时间）
	private Long costTime;
	// 响应错误代码
	private String errorCode;
	// 响应异常信息
	private String errorMsg;
	// 状态
	private String status;

	public Response() {

	}

	public Response(String errorCode, String errorMsg, String status) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.status = status;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

}
