package com.example.GeekificLibraries.part2.generalimportant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount18 {
    private int id;
    private String holderName;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "dd-MM-yyyy hh:mm:ss")
    private Date creationDate;
}
