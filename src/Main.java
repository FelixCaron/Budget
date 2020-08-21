import java.util.Calendar;

public class Main{
public static void main(String[] args) {
    Calendar calTo = Calendar.getInstance();
    Calendar calFrom = Calendar.getInstance();
    calTo.add(Calendar.DAY_OF_MONTH, 23);
    Depense depense = new Depense();
    depense.frequence=Frequence.Unique;
    depense.when=Calendar.getInstance();
    depense.when.add(Calendar.DAY_OF_MONTH, 4);
    depense.montant=15;
    Salaire salaire = new Salaire();
    salaire.frequence=Frequence.Weekly;
    salaire.when=Calendar.getInstance();
    salaire.when.add(Calendar.DAY_OF_MONTH, 1);
    salaire.montant=100;
    DataBase.salaires.add(salaire);
    DataBase.depenses.add(depense);
    Extrapolator ex = new Extrapolator(calFrom, calTo, 0);
    System.out.println(ex.totalAtTo());
}}