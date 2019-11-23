package main.java.space.harbour.java.hw8;

public final class CashDispenser implements CashDispenseChain {
    private String currency;
    private int billsLeft;
    private int value;
    private CashDispenseChain dispenseChain;

    public CashDispenser(final String c, final int bl, final int v) {
        this.currency = c;
        this.value = v;
        this.billsLeft = bl;
    }

    public int getBillsLeft() {
        return billsLeft;
    }

    public int getValue() {
        return billsLeft;
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
        int cash = remainingCash;
        if (cash >= value && billsLeft > 0) {
            int num = cash / value;
            if (num > billsLeft) {
                num = billsLeft;
            }
            cash -= num * value;
            billsLeft -= num;
            System.out.println("Dispensing " + num + " "
                    + value + " " + currency + " notes");
        }
        if (remainingCash > 0) {
            if (nextChain() != null) {
                return nextChain().dispense(remainingCash);
            } else {
                System.out.println("ERROR: Could not dispense"
                        + " the remaining " + cash + " amount.");
                return false;
            }
        } else {
            System.out.println("SUCCESS: Cash dispensed successfully!");
            return true;
        }
    }
}
