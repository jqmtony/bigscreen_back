package com.gochinatv.accelarator.api.util;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * JSON与实体bean之间转换
 * 
 * 
 */
public class JsonUtil {
	private final static ObjectMapper mapper = Common4JsonUtil.getMapperInstance(false);

	/**
	 * bean 2 JSON
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static String beanToJson(Object obj){
		String json = null;
		StringWriter writer = new StringWriter();
		JsonGenerator gen;
		try {
			gen = new JsonFactory().createJsonGenerator(writer);
			mapper.writeValue(gen, obj);
			gen.close();
			json = writer.toString();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return json;
	}

	/**
	 * JSON 2 BEAN
	 * 
	 * @param json
	 * @param cls
	 * @return
	 * @throws Exception
	 */
    public static <T> T jsonToBean(String json, Class<T> cls) throws Exception {
		T vo = mapper.readValue(json, cls);
		return vo;
	}
}
