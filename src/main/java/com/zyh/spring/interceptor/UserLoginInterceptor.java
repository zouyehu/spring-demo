package com.zyh.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserLoginInterceptor implements HandlerInterceptor{

	private static final Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("---访问路径："+request.getRequestURI());
    	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();
    	logger.info("---项目路径："+basePath);
    	
    	if(request.getRequestURI().contains("sysAccess/")){
    		return true;
    	}
    	
    	String conString = request.getHeader("REFERER");
        if(StringUtils.isEmpty(conString)){
        	if(!request.getRequestURI().contains("home/")){
	        	response.sendRedirect(basePath+"/home/index.shtml");
	            return false;
        	}
        }
        
        String user = (String) request.getSession().getAttribute("phone");
        if(user != null){
        	return true;
        }else{
        	response.sendRedirect(basePath+"/home/index.shtml");
        	return false;
        }
    	
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null){
    		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();
    		modelAndView.addObject("basePath", basePath);
    	}
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
