package com.example.GeekificLibraries.part2.deserialize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount9 {
    private int id;
    private String holderName;
    private int balance;

    @JsonCreator
    public BankAccount9(@JsonProperty("name") String name){
        this.holderName = name;
    }
}
