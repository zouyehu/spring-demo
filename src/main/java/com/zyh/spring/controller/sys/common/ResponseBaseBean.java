package com.zyh.spring.controller.sys.common;

import java.io.Serializable;

import com.zyh.spring.utils.StringsUtil;


public class ResponseBaseBean implements Serializable
{
	private static final long serialVersionUID = -848549750337786076L;

	/**
	 * 系统交易号
	 */
	public String  transCode ;
	
	/**
	 * 返回code
	 */
	public String outResultCode;
	
	/**
	 * 请求响应码
	 */
	public String resultCode;
	
	/**
	 * 请求响应文字
	 */
	public String resultMsg;
	
	/**
	 * 返回其他参数
	 */
	public Object responseBody;
	
	public static ResponseBaseBean initerror(String registerResp,ResponseBaseBean baseBean){
		if (StringsUtil.isBlank(registerResp)) {
			baseBean.initStatus(ResponseStatusEnum.RESPONSE_EXTERNAL_ERROR);
		}else{
			baseBean.initStatus(ResponseStatusEnum.RESPONSE_EXTERNAL_ERROR,registerResp);
		}
		return baseBean;

	}

	/**
	 * 初始化网络响应状态
	 * @param status
	 */
	public void initStatus(ResponseStatusEnum status)
	{
		if(status != null)
		{
			this.resultCode = status.resultCode;
			this.resultMsg = status.resultMsg;
		}
	}
	
	/**
	 * 初始化网络响应状态
	 * @param status
	 */
	public void initStatus(ResponseStatusEnum status,String message)
	{
		if(status != null)
		{
			this.resultCode = status.resultCode;
			this.resultMsg = message;
		}
	}
	
	/**
	 * 异常
	 * @param status
	 */
	public void initStatusException(SysException e)
	{
			this.resultCode = e.getCode();
			this.resultMsg = e.getMessage();
	}
	
	public String getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(String resultCode)
	{
		this.resultCode = resultCode;
	}

	public String getResultMsg()
	{
		return resultMsg;
	}

	public void setResultMsg(String resultMsg)
	{
		this.resultMsg = resultMsg;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getOutResultCode() {
		return outResultCode;
	}

	public void setOutResultCode(String outResultCode) {
		this.outResultCode = outResultCode;
	}
	
}
