package main.java.space.harbour.java.hw9;

public interface CashDispenseChain {
    void setNextChain(CashDispenseChain nextChain);
    CashDispenseChain nextChain();
    boolean dispense(int cash);
}
