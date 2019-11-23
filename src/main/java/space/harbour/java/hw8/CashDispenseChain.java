package main.java.space.harbour.java.hw8;

public interface CashDispenseChain {
    void setNextChain(CashDispenseChain nextChain);
    CashDispenseChain nextChain();
    boolean dispense(int cash);
}
