package com.example.GeekificLibraries.part2.deserialize;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount13 {
    private int id;
    @JsonAlias({"Name","name","hName"})
    private String holderName;
    private int balance;
}
