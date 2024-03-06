package com.example.GeekificLibraries.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@ToString(includeFieldNames = false, callSuper = true)
public class Actor4 extends Person{
    private String topRole;

}

