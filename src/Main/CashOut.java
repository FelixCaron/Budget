package Main;

import java.util.Calendar;

public class CashOut extends CashFlow {

    CashOut(float Amount, Calendar When, Frequency f) {
        super(Amount, When, f);
        DataBase.cashOuts.add(this);
    }

}
