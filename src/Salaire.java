import java.util.Calendar;

public class Salaire extends CashFlow{
Salaire(float montant, Calendar when,Frequence f){
    super(montant, when,f);
    DataBase.salaires.add(this);
}

}
