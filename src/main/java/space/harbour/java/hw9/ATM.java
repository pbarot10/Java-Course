package main.java.space.harbour.java.hw9;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public final class ATM extends Observable
        implements Cloneable, Observer, Iterable<CashDispenser> {

    private int atmId;
    private CashDispenser[] cashDispenser;
    private Observer bankDept;

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(final int atmid) {
        this.atmId = atmid;
    }

    public ATM(final List<Integer> denomination,
               final String currency, final int initialNumberOfBills) {
        cashDispenser = new CashDispenser[denomination.size()];
        denomination.sort((a, b) -> b - a);
        for (int i = 0; i < denomination.size(); i++) {
            cashDispenser[i] = new CashDispenser(currency,
                    denomination.get(i), initialNumberOfBills);
            cashDispenser[i].addObserver(this);
        }
        for (int i = 0; i < denomination.size() - 1; i++) {
            cashDispenser[i].setNextChain(cashDispenser[i + 1]);
        }
    }

    @Override
    public void update(final Observable observable, final Object object) {
        System.out.println("ATM is out of the following bills: "
                + ((CashDispenser) object).getDenomination() + " "
                + ((CashDispenser) object).getCurrency());
    }

    @Override
    public void addObserver(final Observer o) {
        super.addObserver(o);
        bankDept = o;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
        bankDept.update(this, this);
    }

    public int getBalance() {
        int bal = 0;
        for (CashDispenser cd: this) {
            bal += cd.getBillsLeft() * cd.getDenomination();
        }
        return bal;
    }

    public boolean withdraw(final int amount) {
        boolean result = cashDispenser[0].dispense(amount);
        if (getBalance() == 0) {
            notifyObservers();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ATM clone = (ATM) super.clone();

        clone.cashDispenser = new CashDispenser[cashDispenser.length];
        for (int i = 0; i < cashDispenser.length; i++) {
            clone.cashDispenser[i] = (CashDispenser)
                    cashDispenser[i].clone();
            clone.cashDispenser[i].addObserver(this);
        }
        for (int i = 0; i < cashDispenser.length - 1; i++) {
            clone.cashDispenser[i].setNextChain(clone.cashDispenser[i + 1]);
        }
        clone.setAtmId(getAtmId());
        return clone;
    }

    public final class CurrencyDispenserIterator implements Iterator {
        private int position;

        CurrencyDispenserIterator(final int p) {
            this.position = p;
        }

        @Override
        public boolean hasNext() {
            return position < cashDispenser.length;
        }

        @Override
        public Object next() {
            return cashDispenser[position++];
        }
    }

    @Override
    public Iterator iterator() {
        return new CurrencyDispenserIterator(0);
    }
}
