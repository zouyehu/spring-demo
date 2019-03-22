package com.zyh.spring.utils;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * JSON 转换工具类
 * 
 * @author HU
 * @date 2019-02-20
 */
public class JsonUtil {

	/**
	 * JSON 转换 Object
	 * 
	 * @param strJson
	 * @param cls
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T jsonToObject(String strJson, Class<T> cls)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(dateFormat);

		if (StringsUtil.isNotBlank(strJson)) {
			return mapper.readValue(strJson, cls);
		}
		return null;
	}

	/**
	 * Object 转换 JSON
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String objectToJsonString(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(dateFormat);
		return mapper.writeValueAsString(object);
	}

	/**
	 * List 转换 JSON
	 * 
	 * @param list
	 * @return
	 */
	public static String listToJson(List<?> list) {
		return JSONArray.toJSON(list).toString();
	}

	/**
	 * Map 转换 JSON
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<?, ?> map) {
		return JSONObject.toJSON(map).toString();
	}

	/**
	 * JSON 转换 List
	 * 
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> jsonToList(String json) {
		JSONArray jsonArr = JSONArray.parseArray(json);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(jsonArr.size());
		for (int i = 0; i < jsonArr.size(); i++) {
			list.add(jsonToMap(jsonArr.getJSONObject(i)));
		}
		return list;
	}

	/**
	 * JSON 转换 Map
	 * 
	 * @param s
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String s) {
		JSONObject jsonObj = JSONObject.parseObject(s);
		return jsonToMap(jsonObj);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(JSONObject jsonObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keys = (Iterator<String>) jsonObj.keySet();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = jsonObj.get(key).toString();
			if (value.startsWith("{") && value.endsWith("}")) {
				map.put(key, jsonToMap(value));
			} else if (value.startsWith("[") && value.endsWith("]")) {
				JSONArray jsonArray = jsonObj.getJSONArray(key);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < jsonArray.size(); i++) {
					list.add(jsonToMap(jsonArray.getString(i)));
				}
				map.put(key, list);
			} else {
				if ("null".equals(value))
					value = null;
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 将 String字符串 转换成 Object
	 * 
	 * @param content
	 * @param clazz
	 * @return
	 */
	public static <T extends Serializable> T contentToObject(String content, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		T object = null;
		try {
			object = mapper.readValue(content, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 将 Object 转换成 String字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToContent(Object object) {
		String content = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			content = mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 将 String 进行转义 传递到前端
	 * 
	 * @param string
	 * @return
	 */
	public static String jsonEscape(String string) {
		if (string.indexOf("'") != -1) {
			// 将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
			string = string.replaceAll("'", "\\'");
		}
		if (string.indexOf("\"") != -1) {
			// 将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
			string = string.replaceAll("\"", "\\\"");
		}
		if (string.indexOf("\r\n") != -1) {
			// 将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
			string = string.replaceAll("\r\n", "\\u000d\\u000a");
		}
		if (string.indexOf("\n") != -1) {
			// 将换行转换一下，因为JSON串中字符串不能出现显式的换行
			string = string.replaceAll("\n", "\\u000a");
		}
		return string;
	}

}
