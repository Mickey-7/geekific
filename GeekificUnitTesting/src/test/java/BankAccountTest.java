import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BankAccountTest {
    private BankAccount ba;

    @BeforeEach
    public void setUp(){
        ba = new BankAccount(500);
    }

    @AfterEach
    public void tearDown(){

    }

    /*
    @BeforeEach
    @Test
    @AfterEach

    @BeforeEach
    @Test
    @AfterEach

    @BeforeEach
    @Test
    @AfterEach

    ------------------------

    @BeforeAll
    @Test
    @Test
    @Test
    @AfterAll

    ------------------------

    IntelliJ plugin - Code Coverage for Java
        - More Run/Debug
            - Run BankAccountTest with Coverage

   ------------------------


   Mockito
    - mocks are dummy objects  used instead
    of the actual implementation
    - purpose is allow out tests to focus on
    a single unit by mocking the external
    dependencies of this unit
    - mocking should be used when the real
    object has a non-deterministic behavior
    or is a callback function or is yet to
    be implemented



    */



    @Test
    public void test_deposit(){
        ba.deposit(100);
        assertEquals(600, ba.getBalance());

    }

    @Test
    public void test_withdraw(){
        assertEquals(460,  ba.withdraw(40));
    }

    @Test
    public void test_withdraw_maxValue(){
        ba.withdraw(150);
        assertEquals(400, ba.getBalance());
    }

    //dealing with incorrect input
    @Test
    public void test_deposit_negativeValue(){
        NumberFormatException thrown = assertThrows(
                NumberFormatException.class,
                () -> ba.deposit(-100)
        );
        assertTrue(thrown.getMessage().contains("Negative"));
    }
}
