package com.zyh.spring.interceptor;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.spring.controller.sys.common.ResponseBaseBean;
import com.zyh.spring.controller.sys.common.ResponseStatusEnum;
import com.zyh.spring.controller.sys.service.JsonConvertProvider;

/**
 * 单点登录
 * @author HU
 * @date 2019-02-20
 */
public class AccessTokenInterceptor implements HandlerInterceptor{

private static final Logger logger = LoggerFactory.getLogger(AccessTokenInterceptor.class);
	
	// ant风格路径匹配
	private AntPathMatcher matcher = new AntPathMatcher();
	
	private List<String> notCheckPatterUrls;
	
	private List<String> notCheckTransCodes;
	
	@Autowired
	private JsonConvertProvider jsonConvertProvider;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		for (String url : notCheckPatterUrls) {
			if (matcher.match(url, uri)) {
				return true;
			}
		}
		
		String transCode = request.getParameter("transCode");
		if (notCheckTransCodes.contains(transCode)) {
			return true;
		}
		
		String appToken = request.getHeader("appToken");
		logger.info("---前端请求传过来的加密token为:" + appToken);
		if (StringUtils.isNotBlank(appToken)) {
			String token = "123123";//从数据库取出的或从redis中拿到的,根据实际业务需求来做
			if (appToken.equals(token)) {
				return true;
			}
			this.writeResponse(response, "会话超时,请重新登录");
			return false;
		}
		return false;
	}
	
	/**
	 * return false时
	 * 返回相应错误信息
	 * @param response
	 * @param errorMsg
	 * @throws IOException
	 */
	private void writeResponse(HttpServletResponse response, String errorMsg)
			throws IOException {
		
		ResponseBaseBean baseBean = new ResponseBaseBean();
		baseBean.initStatus(ResponseStatusEnum.RESPONSE_SESSION_TIMEOUT);
		String json = this.jsonConvertProvider.toJson(baseBean);
		((ServletRequest) response).setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Writer writer = response.getWriter();
		writer.write(json);
		writer.close();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<String> getNotCheckPatterUrls() {
		return notCheckPatterUrls;
	}

	public void setNotCheckPatterUrls(List<String> notCheckPatterUrls) {
		this.notCheckPatterUrls = notCheckPatterUrls;
	}

	public List<String> getNotCheckTransCodes() {
		return notCheckTransCodes;
	}

	public void setNotCheckTransCodes(List<String> notCheckTransCodes) {
		this.notCheckTransCodes = notCheckTransCodes;
	}

	public JsonConvertProvider getJsonConvertProvider() {
		return jsonConvertProvider;
	}

	public void setJsonConvertProvider(JsonConvertProvider jsonConvertProvider) {
		this.jsonConvertProvider = jsonConvertProvider;
	}
	
	
}
