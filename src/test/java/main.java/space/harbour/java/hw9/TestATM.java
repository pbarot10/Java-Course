package main.java.space.harbour.java.hw9;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class TestATM {
    private ATM atm;
    private ArrayList<Integer> denomination;
    private BankDepartment bankDepartment;

    private final int denominationFive = 5;
    private final int denominationTen = 10;
    private final int nBills = 5;
    private final int denominationTwenty = 20;
    private final int denominationFifty = 50;
    private final int denominationHundred = 100;

    private final int atmId1 = 111;
    private final int atmId2 = 112;

    private final int w1 = 150;
    private final int w2 = 135;
    private final int w3 = 600;
    private final int w4 = 325;
    private final int w5 = 625;
    private final int w6 = 1000;

    private String currency = "Euro";

    @Before
    public void setUp() {
        denomination = new ArrayList<>();
        denomination.add(denominationFive);
        denomination.add(denominationTen);
        denomination.add(denominationTwenty);
        denomination.add(denominationFifty);
        denomination.add(denominationHundred);
        atm = new ATM(denomination, currency, nBills);
        atm.setAtmId(atmId1);
        List<ATM> atms = new ArrayList<>();
        atms.add(atm);
        bankDepartment = new BankDepartment(atms);
    }

    @Test
    public void testCloning() throws CloneNotSupportedException {
        ATM atm2 = (ATM) atm.clone();
        atm2.setAtmId(atmId2);
        atm.withdraw(w1);
        atm2.withdraw(w2);
        assertNotEquals(atm.getBalance(), atm2.getBalance());
    }

    @Test
    public void testWithdrawOK() {
        assertTrue(atm.withdraw(w5));
    }

    @Test
    public void testWithdrawNotOK() {
        assertFalse(atm.withdraw(w6));
    }

    @Test
    public void testEmptyAtmNotification() {
        atm.withdraw(nBills * (denominationFifty + denominationFive
                + denominationTen + denominationHundred + denominationTwenty));
        assertEquals(atmId1, bankDepartment.getAtmEmptied());
    }

    @Test
    public void testAtmNotEmptyNotification() {
        atm.withdraw(nBills * (denominationFifty + denominationFive
                + denominationTen));
        assertEquals(-1, bankDepartment.getAtmEmptied());
    }

    @Test
    public void testGetATMBalance() {
        atm.withdraw(w3);
        assertEquals(w4, atm.getBalance());
    }
}
