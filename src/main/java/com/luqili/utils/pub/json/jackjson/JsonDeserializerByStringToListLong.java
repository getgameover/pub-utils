package com.luqili.utils.pub.json.jackjson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
/**
 * 日期反序列化
 * @author luqili 2016年7月21日
 *
 */
public class JsonDeserializerByStringToListLong extends JsonDeserializer<List<Long>> {
	@Override
	public List<Long> deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
		String value = jp.getValueAsString();
		try {
			String[] ls=value.split(",");
			List<Long> ltls=new ArrayList<>();
			for(String l:ls){
				ltls.add(Long.parseLong(l));
			}
			return ltls;
		} catch (Exception e) {
			throw new RuntimeException("Json类型转换错误", e);
		}
	}
}
