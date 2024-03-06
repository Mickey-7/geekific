package com.example.GeekificLibraries.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@ToString(onlyExplicitlyIncluded = true)
public class Person {
    private int id;
    @ToString.Include
    private String name;
}
