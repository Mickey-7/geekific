package com.example.GeekificLibraries.part1;

import com.example.GeekificLibraries.part1.BankAccount;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomSerializer extends StdSerializer<BankAccount> {
    public CustomSerializer(Class<BankAccount> t) {
        super(t);
    }

    @Override
    public void serialize(BankAccount bankAccount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("holderName", bankAccount.getHolderName());
        jsonGenerator.writeEndObject();
    }
}
