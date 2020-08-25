package Main;

import java.util.ArrayList;
import java.util.Calendar;

public class Extrapolator {
Calendar today = Calendar.getInstance();
Calendar from = today;
Calendar to;
ArrayList<Salary> incomes;
ArrayList<CashOut> outcomes;
float initialAmount;
public float totalAtTo(){
    Calendar[] calendarsToFormat={today,from,to};
    setCalendars(calendarsToFormat);
    float IncomeFloat = totalIncome();
    float OutcomeFloat = totalOutcome();
    return initialAmount-OutcomeFloat+IncomeFloat;
    
}

private void setCalendars(Calendar[] calendarsToFormat) {
    for (Calendar calendar : calendarsToFormat) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayofmonth);
    }
}

public float totalIncome() {
    float total = 0;
    for (Salary salary : incomes) {
        total=getTotalFor(salary)+total;
    }
    return total;
    
}
public float totalOutcome() {
    float total = 0;
    for (CashOut cashOut : outcomes) {
        total=getTotalFor(cashOut)+total;
    }
    return total;
}

private float getTotalFor(CashFlow cashFlow) {
    float amount = cashFlow.amount;
    int numberOfTimes = numberOfTimes(cashFlow);
    return amount*numberOfTimes;
}


//calculates from start of from to end of to
private int numberOfTimes(CashFlow cashFlow) {
    Calendar firstDate = cashFlow.when;
    Frequency frequency=cashFlow.frequency;
    
    while (firstDate.before(from)){
        
       switch (frequency) {
            case Unique:
                return 0;
            case Daily:
                firstDate.add(Calendar.DAY_OF_MONTH, 1);
            break;
            case Weekly:
                firstDate.add(Calendar.DAY_OF_MONTH, 7);
            break;
            case BiWeekly:
                firstDate.add(Calendar.DAY_OF_MONTH, 14);
            break;
            case Monthly:
                firstDate.add(Calendar.MONTH, 1);
            break;
            case Annual:
                firstDate.add(Calendar.YEAR, 1);
            break;
            default:
                return 0;
            
       }}
       if (firstDate.after(to)){ return 0;}
       if(firstDate.equals(to)){ return 1;}
       int numberOfTimes = 0;
       while (firstDate.before(to)){
        numberOfTimes++;
        switch (frequency) {
             case Daily:
                 firstDate.add(Calendar.DAY_OF_MONTH, 1);
             break;
             case Weekly:
                 firstDate.add(Calendar.DAY_OF_MONTH, 7);
             break;
             case BiWeekly:
                 firstDate.add(Calendar.DAY_OF_MONTH, 14);
             break;
             case Monthly:
                 firstDate.add(Calendar.MONTH, 1);
             break;
             case Annual:
                 firstDate.add(Calendar.YEAR, 1);
             break;
             case Unique:
                return 1;
             default:
                 return numberOfTimes;
            
        }

    }
    if(firstDate.equals(to)){numberOfTimes++;} // ajustement if payment due on last date
    return numberOfTimes;
}


Extrapolator(Calendar From, Calendar To, ArrayList<Salary> Incomes, ArrayList<CashOut> Outcomes,float InitialAmount) {
    from = From;
    to = To;
    incomes = Incomes;
    outcomes = Outcomes;
    initialAmount= InitialAmount;
}

Extrapolator(Calendar To, ArrayList<Salary> Incomes,ArrayList<CashOut> Outcomes, float InitialAmount){
    this(Calendar.getInstance(), To, Incomes, Outcomes, InitialAmount);
}



Extrapolator(Calendar To,float InitialAmount){
    
    this(Calendar.getInstance(), To, DataBase.salarys, DataBase.cashOuts, InitialAmount);}

public Extrapolator(Calendar calFrom, Calendar calTo, int i) {
    this(calFrom, calTo, DataBase.salarys, DataBase.cashOuts, i);}
}



