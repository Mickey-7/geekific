package com.example.GeekificLibraries.part2.serialize;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@JsonPropertyOrder(alphabetic = true)
@JsonPropertyOrder({"holderName", "balance", "id"})
public class BankAccount4 {
    private int id;
    private String holderName;
    private int balance;
}
