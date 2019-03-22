package com.zyh.spring.controller.sys.service;

import com.zyh.spring.controller.sys.common.ProviderInterface;

/**
 * JSON 转换服务接口
 * 
 * @author HU
 * @version 1.0
 */
public interface JsonConvertProvider extends ProviderInterface {
	
	public String toJson(Object object);

	
	public <T> T toObject(String json, Class<T> clazz);

}
