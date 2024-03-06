package com.example.GeekificLibraries.part2.generalimportant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount17 {
    private Integer id;
    @JsonProperty("name")
    private String holderName;
}
