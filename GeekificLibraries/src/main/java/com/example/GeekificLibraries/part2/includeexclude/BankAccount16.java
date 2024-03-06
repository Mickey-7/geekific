package com.example.GeekificLibraries.part2.includeexclude;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccount16 {
    private int id;
    private String holderName;
    private int balance;
}
