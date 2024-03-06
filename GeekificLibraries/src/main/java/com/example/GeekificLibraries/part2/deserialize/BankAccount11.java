package com.example.GeekificLibraries.part2.deserialize;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount11 {
    private int id;
    private String holderName;

    @JsonSetter("holderName")
    public void  name(String name){
        holderName = name;
    }
}
