package com.example.GeekificLibraries.part2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomBalanceSerializer extends StdSerializer<Integer> {
    public CustomBalanceSerializer(){
        this(null);
    }

    public CustomBalanceSerializer(Class<Integer> t) {
        super(t);
    }

    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(String.valueOf(integer*0.97));
    }
}
