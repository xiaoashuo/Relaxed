package com.relaxed.common.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/10/17 22:19 jackson NULL值序列化为 ""
 */
public class NullStringJsonSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
		jsonGenerator.writeString("");
	}

}
