package com.example.GeekificLibraries.lombok;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Builder
@ToString
public class Actor5 {
    private int id;
    private String name;
    private String topRole;

}
