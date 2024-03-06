package com.example.GeekificLibraries.part2.generalimportant;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class SubAcnt1 {
    private int id;
    private BankAccount20 bankAccount20;
}
