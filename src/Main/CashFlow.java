package Main;

import java.io.Serializable;
import java.util.Calendar;

public abstract class CashFlow implements Serializable{
Frequency frequency;
float amount;
Calendar when;
CashFlow(float Amount, Calendar When, Frequency f){
    amount=Amount;
    when=When;
    frequency=f;
}
}
