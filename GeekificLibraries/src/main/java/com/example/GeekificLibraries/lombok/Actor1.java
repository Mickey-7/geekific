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
@ToString(includeFieldNames = false)
public class Actor1 {
    private int id;
    @Getter(AccessLevel.PROTECTED)
    private String name;
    @Setter(AccessLevel.PACKAGE)
    private String topRole;
    @Getter(lazy = true) // will assigned value when getter is invoked
    private final List<String> data = initData();

    private List<String> initData(){
        return new ArrayList<>();
    }


}
