package com.example.GeekificLibraries.part1;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomDeserializer extends StdDeserializer<BankAccount> {
    public CustomDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BankAccount deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        BankAccount bankAccount = new BankAccount();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String holderName = node.get("holderName").asText();
        bankAccount.setHolderName(holderName);
        return bankAccount;
    }
}
