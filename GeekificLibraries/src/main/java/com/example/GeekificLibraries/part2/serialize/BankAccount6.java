package com.example.GeekificLibraries.part2.serialize;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount6 {
    private int id;
    private String holderName;
    private  int balance;

    @JsonValue
    public String name() {
        return holderName;
    }
}
