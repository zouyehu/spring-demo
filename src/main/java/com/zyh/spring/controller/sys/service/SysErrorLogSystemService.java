package com.zyh.spring.controller.sys.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyh.spring.controller.sys.common.Request;
import com.zyh.spring.controller.sys.common.Response;
import com.zyh.spring.controller.sys.common.ResponseBaseBean;
import com.zyh.spring.controller.sys.common.ResponseStatusEnum;

/**
 * 记录系统请求和响应报文
 * @author HU
 * @date 2019-02-20
 */
public interface SysErrorLogSystemService{
	
	/**
	 * 添加内部接口异常记录
	 * @param request
	 * @param response
	 * @param errorCode
	 * @param errorMsg
	 */
	public void createSystemErrorLog(Request request, Response response, String errorCode, String errorMsg,String uuid,long elapsedTime);
	
	public void createSystemErrorLogInterceptor(HttpServletRequest request, HttpServletResponse response, String errorCode, String errorMsg,String uuid,long elapsedTime);


	/**
	 * 添加与关联系统接口交互的异常记录(外部)
	 * @param user
	 * @param transCode
	 * @param requestMsg
	 * @param responseMsg
	 * @param requestUrl
	 * @param errorCode
	 * @param errorMsg
	 */
	public void createExtErrorLog(String user, String transCode, String requestMsg, String responseMsg, String requestUrl,ResponseStatusEnum resEnum,String uuid,long elapsedTime);
	//添加扩展字节长度
	public void createExtErrorLog2(String user, String transCode, String requestMsg, String responseMsg, String requestUrl,ResponseStatusEnum resEnum,String uuid,long elapsedTime,String ext2);
	
	public void createExtErrorLog(String user, String transCode, String requestMsg, String responseMsg, String requestUrl,ResponseBaseBean baseBean,String uuid,long elapsedTime);

	public void createCKLog(String loginName, String errorSource, String errorCode, String errorMsg,
			String requestUrl, String methodHandler, String transCode, String requestMsg, String responseMsg,
			String serverIp, Date createTime, String ext1, String ext2, String ext3, String resourceType,
			Long elapsedTimSe) ;
	
	public void saveSendReceiveMsg(String loginName,String transcode,String requestMsg,String responseMsg);

}
