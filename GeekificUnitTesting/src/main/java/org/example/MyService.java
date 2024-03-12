package org.example;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class MyService {
    private final MyRepository repository;
    private final OtherService otherService;

    public String getAsJsonString(UUID id){
        MyObject object = repository.getById(id);
        return  otherService.myObjectToJson(object);
    }

}
