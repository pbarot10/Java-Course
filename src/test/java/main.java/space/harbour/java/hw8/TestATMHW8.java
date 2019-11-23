package main.java.space.harbour.java.hw8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestATMHW8 {
    ArrayList<Integer> denomination;
    ATM atm;
    private final int n = 10;
    private final int fifty = 50;
    private final int five = 5;
    private final int ten = 10;
    private final int twenty = 20;
    private final int w1 = 1425;
    private final int w2 = 1427;
    private final int w3 = 4425;
    private final int w4 = 3000;
    private final int fivehundred = 500;
    private final int hundred = 100;

    @Before
    public void setUp() throws Exception {
        denomination = new ArrayList();
        denomination.add(five);
        denomination.add(ten);
        denomination.add(twenty);
        denomination.add(fifty);
        denomination.add(hundred);
        atm = new ATM(denomination, "euro", ten);
    }

    @After
    public void tearDown() throws Exception {
        denomination = null;
        atm = null;
    }

    @Test
    public void testWithdrawingOK() {
        denomination = new ArrayList();
        denomination.add(5);
        denomination.add(10);
        denomination.add(20);
        denomination.add(50);
        denomination.add(100);
        atm = new ATM(denomination, "euro", fivehundred);
        assertFalse(atm.withdraw(w1));
    }

    @Test
    public void testWithdrawingRest() {
        assertFalse(atm.withdraw(w2));
    }

    @Test
    public void testWithdrawingTooMuch() {
        assertFalse(atm.withdraw(five));
    }

    public void testGetBalance() {
        assertEquals(atm.getBalance(), w3);
        testWithdrawingOK();
        assertEquals(atm.getBalance(), w4);
    }
}
