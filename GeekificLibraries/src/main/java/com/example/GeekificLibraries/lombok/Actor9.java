package com.example.GeekificLibraries.lombok;


import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder(setterPrefix = "add")
public class Actor9 {
    private int id;
    private String name;
    private String topRole;
    @Singular
    private List<String> movies;


}

