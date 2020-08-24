import java.util.Calendar;

public class Depense extends CashFlow {

    Depense(float Montant, Calendar quand, Frequence f) {
        super(Montant, quand, f);
        DataBase.depenses.add(this);
    }

}
