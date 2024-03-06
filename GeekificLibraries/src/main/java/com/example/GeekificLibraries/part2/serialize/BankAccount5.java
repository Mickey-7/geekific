package com.example.GeekificLibraries.part2.serialize;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount5 {
    private int id;
    private String holderName;
    @JsonRawValue
    private String json;
}
