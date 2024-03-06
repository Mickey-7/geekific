package com.example.GeekificLibraries.lombok;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
public class Actor7 {
    private int id;
    private String name;
    @Builder.Default
    private String topRole;


}
