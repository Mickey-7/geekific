package com.example.GeekificLibraries.part2.serialize;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonRootName(value = "accountDetails")
@Data
@AllArgsConstructor
public class BankAccount7 {
    private int id;
    private String holderName;
    private  int balance;

}
