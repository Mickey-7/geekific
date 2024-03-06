package com.example.GeekificLibraries.part2.serialize;

import com.example.GeekificLibraries.part2.CustomBalanceSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount8 {
    private int id;
    private String holderName;
    @JsonSerialize(using = CustomBalanceSerializer.class)
    private int balance;
}
