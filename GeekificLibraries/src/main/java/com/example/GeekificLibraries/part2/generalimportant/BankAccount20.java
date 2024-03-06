package com.example.GeekificLibraries.part2.generalimportant;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class BankAccount20 {
    private int id;
    private String holderName;
    List<SubAcnt1> subAcnts1 = new ArrayList<>();
}
