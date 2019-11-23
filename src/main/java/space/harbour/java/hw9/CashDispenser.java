package main.java.space.harbour.java.hw9;

import java.util.Observable;
import java.util.Observer;

public final class CashDispenser
        extends Observable implements CashDispenseChain, Cloneable {
    private String currency;
    private int billsLeft;
    private int denomination;
    private CashDispenseChain dispenseChain;
    private Observer atm;

    public String getCurrency() {
        return currency;
    }

    public CashDispenser(final String c, final int bl, final int d) {
        this.currency = c;
        this.denomination = d;
        this.billsLeft = bl;
    }

    public int getBillsLeft() {
        return billsLeft;
    }

    public int getDenomination() {
        return denomination;
    }

    @Override
    public void setNextChain(final CashDispenseChain nextChain) {
        this.dispenseChain = nextChain;
    }

    @Override
    public CashDispenseChain nextChain() {
        return dispenseChain;
    }

    @Override
    public boolean dispense(final int remainingCash) {
        if (remainingCash == 0) {
            return true;
        }
        if (remainingCash >= denomination && billsLeft > 0) {
            int num = remainingCash / denomination;
            if (num > billsLeft) {
                num = billsLeft;
            }
            int cash = remainingCash - num * denomination;

            if (cash == 0) {
                billsLeft -= num;
                return true;
            }

            if (nextChain() != null) {
                if (nextChain().dispense(cash)) {
                    billsLeft -= num;
                    if (billsLeft == 0) {
                        notifyObservers();
                    }
                    return true;
                }
            }
        }
        if (nextChain() != null) {
            return nextChain().dispense(remainingCash);
        }
        return false;
    }

    @Override
    public synchronized void addObserver(final Observer observer) {
        super.addObserver(observer);
        atm = observer;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CashDispenser clone = (CashDispenser) super.clone();
        clone.currency = currency;
        clone.denomination = denomination;
        clone.billsLeft = billsLeft;
        clone.dispenseChain = dispenseChain;
        return clone;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
        atm.update(this, this);
        System.out.println();
    }
}
