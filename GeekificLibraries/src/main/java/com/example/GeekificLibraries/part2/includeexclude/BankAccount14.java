package com.example.GeekificLibraries.part2.includeexclude;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"balance"})
public class BankAccount14 {
    private int id;
    private String holderName;
    private int balance;
}
