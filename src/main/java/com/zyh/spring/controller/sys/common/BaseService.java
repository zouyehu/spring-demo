package com.zyh.spring.controller.sys.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * 系统基础服务接口
 * @author HU
 *
 */
public interface BaseService {

	ResponseBaseBean doExecute(JSONObject jsonO,HttpServletRequest request, HttpServletResponse response,String uuid);
}
