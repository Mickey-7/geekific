package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyServiceTest {

    @Mock
    private  MyRepository repository;

    @Mock
    private  OtherService otherService;

    @InjectMocks
    private MyService myService;

    @Test
    void myService_calls_otherService_correctly(){
        MyObject myObject = new MyObject();
        when(repository.getById(any())).thenReturn(myObject);
        myService.getAsJsonString(UUID.randomUUID());
        verify(otherService).myObjectToJson(myObject);
    }

    @Test
    void myService_throws_InvalidMyObjectException(){
        when(otherService.myObjectToJson(any())).thenReturn(new InvalidMyObjectException());
        assertThrows(
                InvalidMyObjectException.class,
                () -> myService.getAsJsonString(UUID.randomUUID())
        );
    }


}
