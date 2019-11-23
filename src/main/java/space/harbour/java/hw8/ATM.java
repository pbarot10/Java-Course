package main.java.space.harbour.java.hw8;

import java.util.Iterator;
import java.util.List;

public final class ATM implements Iterable<CashDispenser> {
    private CashDispenser[] cashDispenser;

    public ATM(final List<Integer> denomination,
               final String currency, final int initialNumberOfBills) {
        cashDispenser = new CashDispenser[denomination.size()];
        denomination.sort((a, b) -> b - a);
        for (int i = 0; i < denomination.size(); i++) {
            cashDispenser[i] = new CashDispenser(currency,
                    denomination.get(i), initialNumberOfBills);
        }
        for (int i = 0; i < denomination.size() - 1; i++) {
            cashDispenser[i].setNextChain(cashDispenser[i + 1]);
        }
    }

    public boolean withdraw(final int amount) {
        return cashDispenser[0].dispense(amount);
    }

    public int getBalance() {
        int balance = 0;
        for (CashDispenser dispenser : this) {
            balance += dispenser.getBillsLeft() * dispenser.getValue();
        }
        return balance;
    }

    @Override
    public Iterator<CashDispenser> iterator() {
        return new CashDispenserIterator(0);
    }

    private final class CashDispenserIterator
            implements Iterator<CashDispenser> {
        private int position;

        CashDispenserIterator(final int p) {
            this.position = p;
        }

        @Override
        public boolean hasNext() {
            return this.position < cashDispenser.length;
        }

        @Override
        public CashDispenser next() {
            return cashDispenser[this.position++];
        }
    }
}
