package com.luqili.utils.pub.json.jackjson;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
/**
 * 日期反序列化
 * @author luqili 2016年7月21日
 *
 */
public class JsonDeserializerByDate extends JsonDeserializer<Date> {
	private static String[] parsePatterns=new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm:ss"}; 
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
		String value = jp.getValueAsString();
		try {
			return DateUtils.parseDate(value, parsePatterns);
		} catch (ParseException e) {
			throw  new RuntimeException("Json日期类型错误", e);
		}
	}
}
