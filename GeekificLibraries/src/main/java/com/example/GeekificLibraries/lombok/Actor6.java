package com.example.GeekificLibraries.lombok;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
@ToString
@Builder(
        builderClassName = "ActorBuilder",
        buildMethodName = "execute",
        builderMethodName = "anActor",
        access = AccessLevel.PUBLIC,
        setterPrefix = "set"
)
public class Actor6 {
    private int id;
    private String name;
    private String topRole;


}

