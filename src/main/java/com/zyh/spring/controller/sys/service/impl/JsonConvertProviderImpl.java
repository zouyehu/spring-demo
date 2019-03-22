package com.zyh.spring.controller.sys.service.impl;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zyh.spring.controller.sys.service.JsonConvertProvider;

/**
 * JSON<-->Object 转换工具
 * @author HU
 *
 */
@Component
public class JsonConvertProviderImpl implements JsonConvertProvider {
	private Logger logger = LoggerFactory.getLogger(JsonConvertProviderImpl.class);
	private ObjectMapper mapper = new ObjectMapper();

	public String toJson(Object object) {

		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("object to json error!", e);
			throw new RuntimeException("object to json error!", e);
		}
		return jsonString;
	}

	public <T> T toObject(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("render json to object error!", e);
			throw new RuntimeException("render json to object error!", e);
		}
	}

	public <T> List<T> toObjectList(String json, Class<T> clazz) {
		try {
			mapper.enableDefaultTyping();
			return mapper.readValue(json, new TypeReference<List<T>>() {
			});
		} catch (Exception e) {
			logger.error("render json to object error!", e);
			throw new RuntimeException("from Json发生错误", e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T toCollection(String json, Class<?> collectionClass, Class<?>... clazz) {
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, clazz);
			return (T) mapper.readValue(json, javaType);
		} catch (Exception e) {
			logger.error("render json to object error!", e);
			throw new RuntimeException("from Json发生错误", e);
		}
	}
}
