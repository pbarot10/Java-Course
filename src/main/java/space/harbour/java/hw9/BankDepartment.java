package main.java.space.harbour.java.hw9;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public final class BankDepartment implements Observer {
    private List<ATM> atm;
    private int atmEmptied = -1;

    public int getAtmEmptied() {
        return atmEmptied;
    }

    public BankDepartment(final List<ATM> atms) {
        this.atm = atms;
        for (int i = 0; i < this.atm.size(); i++) {
            this.atm.get(i).addObserver(this);
        }
    }

    @Override
    public void update(final Observable observable, final Object object) {
        System.out.println("ATM ID #"
                + ((ATM) observable).getAtmId() + " is out of cash.");
        atmEmptied = ((ATM) observable).getAtmId();
    }
}
