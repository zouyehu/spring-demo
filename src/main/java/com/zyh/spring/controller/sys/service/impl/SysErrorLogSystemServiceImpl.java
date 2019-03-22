package com.zyh.spring.controller.sys.service.impl;

import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.spring.controller.sys.common.Request;
import com.zyh.spring.controller.sys.common.Response;
import com.zyh.spring.controller.sys.common.ResponseBaseBean;
import com.zyh.spring.controller.sys.common.ResponseStatusEnum;
import com.zyh.spring.controller.sys.entity.SysErrorLogSystem;
import com.zyh.spring.controller.sys.repository.SysErrorLogSystemRepository;
import com.zyh.spring.controller.sys.service.EupTransactional;
import com.zyh.spring.controller.sys.service.JsonConvertProvider;
import com.zyh.spring.controller.sys.service.SysErrorLogSystemService;
import com.zyh.spring.utils.StringsUtil;

@Service
public class SysErrorLogSystemServiceImpl implements SysErrorLogSystemService {

	@Autowired
	private SysErrorLogSystemRepository sysErrorLogSystemRepository;

	@Autowired
	private JsonConvertProvider jsonConvertProvider;

	private static final String LOGIN_NAME = "ck_login_Name";

	@Override
	@EupTransactional(value = "CKErrorLogSystemServiceImpl.createSystemErrorLog", timeout = 10, rollbackFor = Exception.class)
	public void createSystemErrorLog(Request request, Response response, String errorCode, String errorMsg, String uuid,
			long elapsedTime) {

		SysErrorLogSystem logSys = new SysErrorLogSystem();
		Object loginName = request.getSessionAttr(LOGIN_NAME);
		if (loginName != null) {
			logSys.setLoginName(loginName.toString());
		}

		logSys.setMethodHandler(request.getMethod());
		logSys.setRequestUrl(request.getContextPath());
		logSys.setServerIp(request.getServerIp());

		String transCode = request.getTransCode();

		/*
		 * if(StringUtil.isNotBlank(transCode)) { logSys.setErrorSource("1"); } else {
		 * logSys.setErrorSource("0"); }
		 */
		logSys.setTransCode(transCode);

		logSys.setErrorMsg(errorMsg);
		logSys.setErrorCode(errorCode);

		if (StringsUtil.isBlank(errorCode) && StringsUtil.isBlank(errorMsg)) {
			logSys.setExt1("0"); // 正常日志
		} else {
			logSys.setExt1("1"); // 异常日志
		}

//		logSys.setRequestMsg(request.getRequestJson());		
		// 防止请求报文太长
		if (request != null) {
			String requestJson = request.getRequestJson();
			if (StringsUtil.isBlank(requestJson) || requestJson.length() <= 3000) {
				logSys.setRequestMsg(requestJson);
			} else {
				logSys.setRequestMsg("请求报文过长");
			}
		}

		if (response != null) {
			String responseJson = jsonConvertProvider.toJson(response);
			if (StringsUtil.isBlank(responseJson) || responseJson.length() <= 3000) {
				logSys.setResponseMsg(responseJson);
			} else {
				logSys.setResponseMsg(responseJson.substring(0, 3000));
			}
		}

		logSys.setCreateTime(new Date());
		logSys.setResourceType("0");
		logSys.setExt3(uuid);
		logSys.setElapsedTime(elapsedTime);
		sysErrorLogSystemRepository.save(logSys);
	}

	@Override
	public void saveSendReceiveMsg(String loginName, String transCode, String requestMsg, String responseMsg) {
		SysErrorLogSystem logSys = new SysErrorLogSystem();
		logSys.setLoginName(loginName);
		logSys.setTransCode(transCode);
		logSys.setRequestMsg(requestMsg);
		logSys.setResponseMsg(responseMsg);
		logSys.setCreateTime(new Date());
		sysErrorLogSystemRepository.save(logSys);
	};

	@Override
	public void createSystemErrorLogInterceptor(HttpServletRequest servletRequest, HttpServletResponse serlvetResponse,
			String errorCode, String errorMsg, String uuid, long elapsedTime) {

		Response response = null;
		String transCode = servletRequest.getParameter("transCode") == null ? ""
				: servletRequest.getParameter("transCode");
		String requestNo = servletRequest.getParameter("requestNo") == null ? ""
				: servletRequest.getParameter("requestNo");
		String requestBodyJson = scriptingFilter(servletRequest.getParameter("requestBodyJson") == null ? ""
				: servletRequest.getParameter("requestBodyJson"));
		Request request = Request.createRequestHeader(transCode, "html5");

		try {

			if (!StringsUtil.isNull(requestNo)) {
				request.setRequestNo(requestNo);
			}
			String no = MDC.get("RequestNo");
			if (StringsUtil.isNull(no)) {
				MDC.put("RequestNo", request.getRequestNo());
			}

			request.setClientIp(servletRequest.getRemoteHost());
			request.setHttpResponse(serlvetResponse);
			request.setRequestJson(requestBodyJson);
			request.setHttpRequest(servletRequest);
			request.setHttpSession(servletRequest.getSession());
		} catch (Exception e) {
		}
		this.createSystemErrorLog(request, response, errorCode, errorMsg, uuid, elapsedTime);

	}

	/** 敏感字符替换 */
	public String scriptingFilter(String value) {
		String str = Pattern.compile("<script.*?>", Pattern.CASE_INSENSITIVE).matcher(value)
				.replaceAll("&lt;/script&gt;");
		str = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("&lt;/script&gt;");
		str = Pattern.compile("<iframe.*?>", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("&lt;/iframe&gt;");
		str = Pattern.compile("</iframe>", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("&lt;/iframe&gt;");
		return str;
	}

	@Override
	@EupTransactional(value = "CKErrorLogSystemServiceImpl.createExtErrorLog", timeout = 10, rollbackFor = Exception.class)
	public void createExtErrorLog(String user, String transCode, String requestMsg, String responseMsg,
			String requestUrl, ResponseStatusEnum resEnum, String uuid, long elapsedTime) {

		SysErrorLogSystem logExt = new SysErrorLogSystem();

		logExt.setLoginName(user);
		logExt.setErrorCode(resEnum.resultCode);
		logExt.setErrorMsg(resEnum.resultMsg);
		logExt.setRequestUrl(requestUrl);

		logExt.setTransCode(transCode);

		if (StringsUtil.isBlank(requestMsg) || requestMsg.length() <= 3000) {
			logExt.setRequestMsg(requestMsg);
		} else {
			logExt.setResponseMsg(requestMsg.substring(0, 3000));
		}
		logExt.setResourceType("1");// 0-內部來源 1-外部（外部係統）
		if (StringsUtil.isBlank(responseMsg) || responseMsg.length() <= 3000) {
			logExt.setResponseMsg(responseMsg);
		} else {
			logExt.setResponseMsg(responseMsg.substring(0, 3000));
		}
		if ("0000".equals(resEnum.resultCode)) {
			logExt.setExt1("0"); // 正常日志
		} else {
			logExt.setExt1("1"); // 异常日志
		}
		logExt.setCreateTime(new Date());
		logExt.setExt3(uuid);
		logExt.setElapsedTime(elapsedTime);
		;
		sysErrorLogSystemRepository.save(logExt);
	}

	@Override
	@EupTransactional(value = "CKErrorLogSystemServiceImpl.createExtErrorLog", timeout = 10, rollbackFor = Exception.class)
	public void createExtErrorLog2(String user, String transCode, String requestMsg, String responseMsg,
			String requestUrl, ResponseStatusEnum resEnum, String uuid, long elapsedTime, String ext2) {

		SysErrorLogSystem logExt = new SysErrorLogSystem();

		logExt.setLoginName(user);
		logExt.setErrorCode(resEnum.resultCode);
		logExt.setErrorMsg(resEnum.resultMsg);
		logExt.setRequestUrl(requestUrl);
		logExt.setTransCode(transCode);
		logExt.setExt2(ext2);
		logExt.setRequestMsg(requestMsg);

		logExt.setResourceType("1");// 0-內部來源 1-外部（外部係統）
		if (StringsUtil.isBlank(responseMsg) || responseMsg.length() <= 3000) {
			logExt.setResponseMsg(responseMsg);
		} else {
			logExt.setResponseMsg(responseMsg.substring(0, 3000));
		}
		if ("0000".equals(resEnum.resultCode)) {
			logExt.setExt1("0"); // 正常日志
		} else {
			logExt.setExt1("1"); // 异常日志
		}
		logExt.setCreateTime(new Date());
		logExt.setExt3(uuid);
		logExt.setElapsedTime(elapsedTime);
		;
		sysErrorLogSystemRepository.save(logExt);
	}

	@Override
	@EupTransactional(value = "CKErrorLogSystemServiceImpl.createExtErrorLog", timeout = 10, rollbackFor = Exception.class)
	public void createExtErrorLog(String user, String transCode, String requestMsg, String responseMsg,
			String requestUrl, ResponseBaseBean baseBean, String uuid, long elapsedTime) {
		SysErrorLogSystem logExt = new SysErrorLogSystem();

		logExt.setLoginName(user);
		logExt.setErrorCode(baseBean.resultCode);
		logExt.setErrorMsg(baseBean.resultMsg);
		logExt.setRequestUrl(requestUrl);

		logExt.setTransCode(transCode);

		if (StringsUtil.isBlank(requestMsg) || requestMsg.length() <= 3000) {
			logExt.setRequestMsg(requestMsg);
		} else {
			logExt.setRequestMsg(requestMsg.substring(0, 3000));
		}

		logExt.setResourceType("1");// 0-內部來源 1-外部（外部係統）
		if (StringsUtil.isBlank(responseMsg) || responseMsg.length() <= 3000) {
			logExt.setResponseMsg(responseMsg);
		} else {
			logExt.setResponseMsg(responseMsg.substring(0, 3000));
		}
		if ("0000".equals(baseBean.getResultCode())) {
			logExt.setExt1("0"); // 正常日志
		} else {
			logExt.setExt1("1"); // 异常日志
		}
		logExt.setCreateTime(new Date());
		logExt.setExt3(uuid);
		logExt.setElapsedTime(elapsedTime);
		;
		sysErrorLogSystemRepository.save(logExt);
	}

	@Override
	public void createCKLog(String loginName, String errorSource, String errorCode, String errorMsg, String requestUrl,
			String methodHandler, String transCode, String requestMsg, String responseMsg, String serverIp,
			Date createTime, String ext1, String ext2, String ext3, String resourceType, Long elapsedTime) {

		SysErrorLogSystem logExt = new SysErrorLogSystem();
		if (!StringsUtil.isNull(loginName)) {
			logExt.setLoginName(loginName);
		}
		logExt.setErrorSource(errorSource);
		logExt.setErrorCode(errorCode);
		logExt.setErrorMsg(errorMsg);
		logExt.setRequestUrl(requestUrl);
		logExt.setMethodHandler(methodHandler);
		logExt.setTransCode(transCode);

		if (StringsUtil.isBlank(requestMsg) || requestMsg.length() <= 3000) {
			logExt.setRequestMsg(requestMsg);
		} else {
			logExt.setRequestMsg(requestMsg.substring(0, 3000));
		}

		if (StringsUtil.isBlank(responseMsg) || responseMsg.length() <= 3000) {
			logExt.setResponseMsg(responseMsg);
		} else {
			logExt.setResponseMsg(responseMsg.substring(0, 3000));
		}
		logExt.setServerIp(serverIp);
		logExt.setCreateTime(createTime);
		logExt.setExt1(ext1);
		logExt.setExt2(ext2);
		logExt.setExt3(ext3);
		logExt.setResourceType(resourceType);
		logExt.setElapsedTime(elapsedTime);
		sysErrorLogSystemRepository.save(logExt);
	}

}
