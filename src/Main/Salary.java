package Main;

import java.util.Calendar;

public class Salary extends CashFlow{
Salary(float montant, Calendar when,Frequency f){
    super(montant, when,f);
    DataBase.salarys.add(this);
}

}
