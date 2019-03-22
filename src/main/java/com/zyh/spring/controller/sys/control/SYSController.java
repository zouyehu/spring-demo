package com.zyh.spring.controller.sys.control;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zyh.spring.controller.sys.common.ResponseBaseBean;
import com.zyh.spring.controller.sys.common.ResponseStatusEnum;
import com.zyh.spring.controller.sys.common.SysException;
import com.zyh.spring.controller.sys.service.SysErrorLogSystemService;
import com.zyh.spring.utils.AccessTokenEncryptUtil;
import com.zyh.spring.utils.ApplicationContextUtil;
import com.zyh.spring.utils.CookieUtils;
import com.zyh.spring.utils.DesCryptUtil;
import com.zyh.spring.utils.JsonUtil;
import com.zyh.spring.utils.StringsUtil;

/**
 * 为系统业务提供接口服务
 * 
 * @author HU
 * @date 2019-02-20
 */
@Controller
@RequestMapping("/sysAccess")
public class SYSController {

	private Logger logger = LoggerFactory.getLogger(SYSController.class);
	private final String key = "6642ADGJ";
	private final String secretKey = "hugenihao123";

	@Autowired
	private SysErrorLogSystemService sysErrorLogSystemService;

	private ResponseBaseBean resp = new ResponseBaseBean();

	private DesCryptUtil desUtil = new DesCryptUtil();

	/**
	 * 接收客户端提交的请求 交易主体内容JSON格式，每个交易主体属性各不相同
	 * 
	 * @param requestBodyJson
	 * 
	 * @param clientId        设备号
	 * @param transCode       交易号
	 * @param appId           RESERVED
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/sysSubmit")
	public Object ftpGetApi(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "") String requestBodyJson, @RequestParam(defaultValue = "") String transCode,
			@RequestParam(defaultValue = "") String requestInfo, @RequestParam(defaultValue = "") String requestNo) {

		String uuid = UUID.randomUUID().toString();
		ResponseBaseBean resp = new ResponseBaseBean();
		resp.initStatus(ResponseStatusEnum.RESPONSE_SUCCESS);
		resp.setTransCode(transCode);
		String requestBody = requestBodyJson;
		if ("1".equals(requestInfo)) {
			try {
				requestBody = desUtil.decode(requestBodyJson, key, "UTF-8");
			} catch (Exception e) {
				resp.initStatus(ResponseStatusEnum.RESPONSE_FAILED);
				resp.setTransCode(transCode);
				sysErrorLogSystemService.createCKLog(null, null, ResponseStatusEnum.RESPONSE_FAILED.resultCode,
						ResponseStatusEnum.RESPONSE_FAILED.resultMsg, request.getContextPath(), "POST", transCode,
						requestBody, e.getMessage(), this.getServerIp(request), new Date(), "1", null, uuid, "1", null);

				return resp;
			}
		}
		logger.info("---APP入参:" + transCode + requestBody);
		JSONObject jsonO = JSONObject.parseObject(requestBody);
		if (!StringsUtil.isNull(transCode)) {
			try {
				Object service = ApplicationContextUtil.getBean(transCode + "Service");
				Method functionName = service.getClass().getDeclaredMethod("doExecute", JSONObject.class,
						HttpServletRequest.class, HttpServletResponse.class, String.class);
				resp = (ResponseBaseBean) functionName.invoke(service, jsonO, request, response, uuid);
				logger.info("---APP出参:" + transCode + JsonUtil.objectToContent(resp));

			} catch (NoSuchMethodException e) {
				logger.error("---1-请求交易号为:" + transCode + "-异常信息:", e);
				resp.initStatus(ResponseStatusEnum.RESPONSE_TRANSCODE_WORNG);
			} catch (SecurityException e) {
				logger.error("---2-请求交易号为:" + transCode + "-异常信息:", e);
				resp.initStatus(ResponseStatusEnum.RESPONSE_TRANSCODE_WORNG);
			} catch (IllegalAccessException e) {
				logger.error("---3-请求交易号为:" + transCode + "-异常信息:", e);
				resp.initStatus(ResponseStatusEnum.RESPONSE_TRANSCODE_WORNG);
			} catch (SysException e) {
				logger.error("---4-请求交易号为:" + transCode + "-异常信息:", e);
				resp.initStatus(ResponseStatusEnum.RESPONSE_TRANSCODE_WORNG);
			} catch (IllegalArgumentException e) {
				logger.error("---5-请求交易号为:" + transCode + "-异常信息:", e);
				resp.initStatus(ResponseStatusEnum.RESPONSE_TRANSCODE_WORNG);
			} catch (InvocationTargetException e) {
				try {
					throw e.getTargetException();
				} catch (SysException sysException) {
					try {

						sysErrorLogSystemService.createCKLog(String.valueOf(jsonO.get("loginName")), null, null, null,
								request.getContextPath(), "POST", transCode, requestBody,
								JsonUtil.objectToContent(resp), this.getServerIp(request), new Date(), "0", null, uuid,
								"0", 0L);

					} catch (Exception a) {
						logger.error("---6-请求交易号为:" + transCode + "-日志记录异常信息:", a);

					}
					resp.initStatusException(sysException);
				} catch (Throwable throwable) {
					try {

						sysErrorLogSystemService.createCKLog(String.valueOf(jsonO.get("loginName")), null, null, null,
								request.getContextPath(), "POST", transCode, requestBody, throwable.getMessage(),
								this.getServerIp(request), new Date(), "0", null, uuid, "0", 0L);

					} catch (Exception a) {
						logger.error("---7-请求交易号为:" + transCode + "-日志记录异常信息:", a);
					}
					logger.error("---8-请求交易号为:" + transCode + "-接口抛出异常:", throwable);
					resp.initStatus(ResponseStatusEnum.RESPONSE_FAILED);
				}
			} catch (NoSuchBeanDefinitionException e) {
				logger.error(transCode + "异常信息" + ":", e);
				resp.initStatus(ResponseStatusEnum.RESPONSE_TRANSCODE_WORNG);
			} catch (Exception e) {
				logger.error("---9-请求交易号为:" + transCode + "-异常信息:", e);
				resp.initStatus(ResponseStatusEnum.RESPONSE_FAILED);
			}
		} else {
			resp.initStatus(ResponseStatusEnum.RESPONSE_TRANSCODE_EMPTOY);
		}

		response.setContentType("application/json;charset=UTF-8");

		if ("1".equals(requestInfo)) {

			String outStr = resp.getResponseBody() == null ? null : JsonUtil.objectToContent(resp.getResponseBody());

			if (outStr != null) {
				try {
					outStr = desUtil.encode(outStr, key, "UTF-8");
				} catch (Exception e) {
					// 解密失败
					resp.initStatus(ResponseStatusEnum.RESPONSE_ILLEGAL_TRANSACTION);
					resp.setTransCode(transCode);
					sysErrorLogSystemService.createCKLog(null, null,
							ResponseStatusEnum.RESPONSE_NETWORK_ERROR.resultCode,
							ResponseStatusEnum.RESPONSE_NETWORK_ERROR.resultMsg, request.getContextPath(), "POST",
							transCode, requestBody, e.getMessage(), this.getServerIp(request), new Date(), "1", null,
							uuid, "1", null);

					logger.error("---10-请求交易号为:" + transCode + ResponseStatusEnum.RESPONSE_NETWORK_ERROR + "-解密失败异常:",
							e);

					return resp;
				}
				resp.setResponseBody(outStr);
			}
		}
		return resp;
	}

	/**
	 * 提供测试页面
	 * 供开发测试接口使用
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/interface")
	public String interfaceTest(ModelMap model) throws Exception {
		return "test/testInterface";
	}

	@SuppressWarnings("unchecked")
	public ResponseBaseBean preHandle(HttpServletRequest request, HttpServletResponse response) {

		String transCode = request.getParameter("transCode");
		String clientIp = request.getRemoteHost();
		logger.info("---获取到cookie中所有accessToken值{}", request.getCookies().toString());
		ArrayList<String> cookieVals = new ArrayList<String>();
		try {
			cookieVals = CookieUtils.getValues(request, "CK_ACCESS_TOKEN");
		} catch (UnsupportedEncodingException e1) {
			logger.error(e1.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (cookieVals.size() == 0) {
			logger.error("{}:CK_ACCESS_TOKEN为空,tansCode:{}", clientIp, transCode);
			resp.initStatus(ResponseStatusEnum.RESPONSE_USER_ISNOT_LOGIN);
			return resp;
		}
		// 解密token值
		logger.info("---拿到accessToken值为:" + cookieVals.toString() + "-用于加密key值为:" + secretKey);
		try {
			for (int i = 0; i < cookieVals.size(); ++i) {
				AccessTokenEncryptUtil.decryption(cookieVals.get(i), secretKey);
			}
		} catch (Exception e) {
			logger.error("---11-请求交易号为:" + transCode + ResponseStatusEnum.RESPONSE_NETWORK_ERROR + "-解密失败异常:", e);
			CookieUtils.clearCookieVal(request, "ACCESS_TOKEN");
			resp.initStatus(ResponseStatusEnum.RESPONSE_USER_INVALID_ORDER);
			return resp;
		}
		// 去校验token的有效性 因为现在是手机号
		String token = null;
		for (int j = 0; j < cookieVals.size(); ++j) {
			token = "qwer1234";// 此值从数据库拿或从缓存Redis中拿到的
			if (token != null) {
				break;
			}
		}
		if (token == null) {
			logger.error("---从数据库查询ACCESS_TOKEN为空,服务IP:" + clientIp + "接口交易号:" + transCode);
			resp.initStatus(ResponseStatusEnum.RESPONSE_SESSION_TIMEOUT);
			return resp;
		}
		// 依据token查询用户信息,此处结合实际业务来实现
		// 把登陆用户名放进Session中
		request.getSession().setAttribute("loginName", "用户名");

		return resp;
	}

	public String getServerIp(HttpServletRequest httpRequest) {
		if (httpRequest == null) {
			return "";
		}
		String ip = httpRequest.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpRequest.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = httpRequest.getRemoteAddr();
		}
		return ip;
	}

}
