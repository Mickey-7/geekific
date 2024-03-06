package com.example.GeekificLibraries.part2.deserialize;

import com.example.GeekificLibraries.part2.CustomBalanceDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount12 {
    private int id;
    private String holderName;
    @JsonDeserialize(using = CustomBalanceDeserializer.class)
    private int balance;
}
