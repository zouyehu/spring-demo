package com.zyh.spring.controller.sys.common;

public enum ResponseStatusEnum {

	/**
	 * 【网络接口响应】--操作成功
	 */
	RESPONSE_SUCCESS("0000", "操作成功"),
	
	/**
	 * 【网络接口响应】--操作失败
	 */
	RESPONSE_FAILED("9999", "操作失败"),
	
	/**
	 * 【网络接口响应】--网络异常
	 */
	RESPONSE_NETWORK_ERROR("1111", "网络异常,请稍后重试"),
	
	RESPONSE_EXTERNAL_ERROR("0001","调度外部接口返回失败"),
	
	RESPONSE_SESSION_TIMEOUT("0002","会话超时,请重新登录"),
	
	RESPONSE_TRANSCODE_WORNG("0003", "请输入正确交易号"),
	
	RESPONSE_TRANSCODE_EMPTOY("0004", "接口交易号为空"),
	
	RESPONSE_ILLEGAL_TRANSACTION("0005", "非法交易"),
	
	RESPONSE_USER_ISNOT_LOGIN("0006", "当前用户未登陆"),
	
	RESPONSE_USER_ISNOT_FIND("0007", "未找到当前用户信息"),
	
	RESPONSE_USER_INVALID_ORDER("0008","令签无效，请先登录"),
	
	RESPONSE_BICHUAN_PARAMES_EMPTOY("0009","必传参数未传值,请检验"),
	;
	
	
	/**
	 * 交易编号
	 */
	public final String resultCode;

	/**
	 * 响应字符文本
	 */
	public final String resultMsg;

	private ResponseStatusEnum(String resultCode, String resultMsg)
	{
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	
}
