package com.example.GeekificLibraries.part2.deserialize;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class BankAccount10 {
    private int id;
    private String holderName;
    private Map<String, String> properties = new HashMap<>();

    public BankAccount10() {
    }

    @JsonAnySetter
    public void add(String key, String value){
        properties.put(key, value);
    }
}
