import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//public class MockitoTest {
//    @Test
//    void mock_list_returns_element(){
//        List<String> mockList = mock(ArrayList.class);
//        when(mockList.get(0)).thenReturn("Like");
//        assertEquals("Like", mockList.get(0));
//    }
//}

//other implementation

//@ExtendWith(MockitoExtension.class)
//public class MockitoTest {
//
//    @Mock
//    List<String> mockList;
//
//    @Test
//    void mock_list_returns_element(){
//        when(mockList.get(0)).thenReturn("Like");
//        assertEquals("Like", mockList.get(0));
//    }
//}

/* spy - create a real object and spy on it */
@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    @Spy
    List<String> spyList = new LinkedList<>();

    @Captor
    ArgumentCaptor<String> captor;

    @Test
    void spy_list_add_element_to_it_correctly(){
        spyList.add("Geekific");
        verify(spyList).add(captor.capture());
        assertEquals("Geekific", captor.getValue());
    }
}