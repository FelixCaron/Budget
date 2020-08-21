import java.util.*;

public class Extrapolator {
Calendar today = Calendar.getInstance();
Calendar from = today;
Calendar to;
ArrayList<Salaire> incomes;
ArrayList<Depense> outcomes;
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

private float totalIncome() {
    float total = 0;
    for (Salaire salaire : incomes) {
        total=getTotalFor(salaire)+total;
    }
    return total;
    
}
private float totalOutcome() {
    float total = 0;
    for (Depense depense : outcomes) {
        total=getTotalFor(depense)+total;
    }
    return total;
}

private float getTotalFor(CashFlow cashFlow) {
    float montant = cashFlow.montant;
    int nombreDeFois = nombreDeFois(cashFlow);
    return montant*nombreDeFois;
}



private int nombreDeFois(CashFlow cashFlow) {
    Calendar firstDate = cashFlow.when;
    Frequence frequence=cashFlow.frequence;
    
    while (firstDate.before(from)){
        
       switch (frequence) {
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
        switch (frequence) {
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
    return numberOfTimes;
}

// contructeur principal, est appelé par les autres constructeurs
Extrapolator(Calendar From, Calendar To, ArrayList<Salaire> Incomes, ArrayList<Depense> Outcomes,float InitialAmount) {
    from = From;
    to = To;
    incomes = Incomes;
    outcomes = Outcomes;
    initialAmount= InitialAmount;
}
//autres constructeurs
Extrapolator(Calendar To, ArrayList<Salaire> Incomes,ArrayList<Depense> Outcomes, float InitialAmount){
    this(Calendar.getInstance(), To, Incomes, Outcomes, InitialAmount);
}



Extrapolator(Calendar To,float InitialAmount){
    
    this(Calendar.getInstance(), To, DataBase.salaires, DataBase.depenses, InitialAmount);}

public Extrapolator(Calendar calFrom, Calendar calTo, int i) {
    this(calFrom, calTo, DataBase.salaires, DataBase.depenses, i);}
}



