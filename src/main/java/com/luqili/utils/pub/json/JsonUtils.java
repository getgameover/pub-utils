package com.luqili.utils.pub.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luqili.utils.pub.valid.ValidatorUtil;

public class JsonUtils {

	/**
	 * 反序列化,指定类型
	 * 
	 * @param json
	 * @param _class
	 * @param typeClass
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> _class, Class<?>... typeClass) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		T object = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
			if (json != null && !"".equals(json)) {
				JavaType javeType = mapper.getTypeFactory().constructParametricType(_class, typeClass);
				object = mapper.readValue(json, javeType);
			}
		} catch (Exception e) {
			throw new RuntimeException("JSON解析错误", e);
		}
		return object;
	}

	/**
	 * 获取一个指定的属性
	 * 
	 * @param json
	 * @param nodeName
	 * @param _class
	 * @return
	 */
	public static <T> T fromJson(String json, String nodeName, Class<T> _class) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		T object = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(json); // 读取Json
			JsonNode strs = rootNode.path(nodeName);
			if (!strs.isNull() && !strs.isMissingNode()) {
				object = mapper.readValue(strs.toString(), _class);
			}
		} catch (Exception e) {
			throw new RuntimeException("JSON解析错误", e);
		}
		return object;
	}

	/**
	 * 反序列化,并验证数据
	 * 
	 * @param json
	 * @param _class
	 * @return
	 */
	public static <T> T fromJsonValidate(String json, Class<T> _class, Class<?>... typeClass) {
		if (StringUtils.isBlank(json)) {
			throw new RuntimeException("未查询到请求参数信息");
		}
		T obj = null;
		try {
			obj = fromJson(json, _class, typeClass);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		String vMsg = ValidatorUtil.validateHtml(obj);
		if (StringUtils.isNotBlank(vMsg)) {
			throw new RuntimeException(vMsg);
		}
		return obj;
	}

	/**
	 * 序列化指定对象
	 * 
	 * @param o
	 * @return
	 */
	public static String toJson(Object o) {
		StringWriter sw = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		if (null != o) {
			try {
				mapper.writeValue(sw, o);
			} catch (IOException e) {
				throw new RuntimeException("JSON序列化错误", e);
			}
		}
		return sw.toString();
	}

	/**
	 * 序列化对象
	 * 
	 * @param o
	 * @param outNull
	 *            是否输出空值
	 * @return
	 */
	public static String toJson(Object o, Boolean outNull) {
		StringWriter sw = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		if (null != o) {
			try {
				if (outNull != null && !outNull) {
					mapper.setSerializationInclusion(Include.NON_NULL);
				}

				mapper.writeValue(sw, o);
			} catch (IOException e) {
				throw new RuntimeException("JSON序列化错误", e);
			}
		}
		return sw.toString();
	}

	/**
	 * 字典排序
	 * 
	 * @param o
	 * @return
	 */
	public static String orderToJson(Object o) {
		StringWriter sw = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
		mapper.setSerializationInclusion(Include.NON_NULL);// 去除空值
		if (null != o) {
			try {
				mapper.writeValue(sw, o);
			} catch (IOException e) {
				throw new RuntimeException("JSON序列化错误", e);
			}
		}
		return sw.toString();
	}

	/**
	 * 将对象转换为Map
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toHashMap(Object src) {
		return src != null ? fromJson(toJson(src), HashMap.class, String.class, Object.class) : null;
	}
}
