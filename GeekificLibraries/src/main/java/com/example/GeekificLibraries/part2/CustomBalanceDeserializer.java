package com.example.GeekificLibraries.part2;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomBalanceDeserializer extends StdDeserializer<Integer> {
    public CustomBalanceDeserializer(){
        this(null);
    }
    public CustomBalanceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return jsonParser.getIntValue()*2;
    }
}
