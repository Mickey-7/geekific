package com.example.GeekificLibraries.part2.generalimportant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SubAcnt {
    private int id;
    @JsonBackReference
    private BankAccount19 bankAccount19;
}
