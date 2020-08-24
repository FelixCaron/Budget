import java.util.Calendar;

public abstract class CashFlow {
Frequence frequence;
float montant;
Calendar when;
CashFlow(float Montant, Calendar quand, Frequence f){
    montant=Montant;
    when=quand;
    frequence=f;
}
}
