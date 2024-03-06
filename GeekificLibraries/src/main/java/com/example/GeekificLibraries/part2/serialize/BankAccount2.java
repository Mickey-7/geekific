package com.example.GeekificLibraries.part2.serialize;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount2 {
    private int id;
    private String holderName;
    @JsonGetter
    public String name(){
        return holderName;
    }
}
