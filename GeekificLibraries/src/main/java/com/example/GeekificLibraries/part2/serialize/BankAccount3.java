package com.example.GeekificLibraries.part2.serialize;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class BankAccount3 {
    private int id;
    private  String holderName;
    private Map<String, String> properties;

    @JsonAnyGetter
    public Map<String, String> getProperties(){
        return  properties;
    }
}
