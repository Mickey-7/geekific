package com.example.GeekificLibraries.part2.generalimportant;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount19 {
    private int id;
    private String holderName;
    @JsonManagedReference
    List<SubAcnt> subAcnts = new ArrayList<>();


}
