import java.io.Console;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Calendar.Builder;

import javax.swing.plaf.synth.SynthStyle;
import javax.xml.crypto.Data;

public class CmdLnApp {
    private static final boolean Salaire = true;
    private static final boolean Depense = false;
    Scanner in;

    public static void launch() {
        new CmdLnApp();

    }

    CmdLnApp() {
        System.out.println("App is started, waiting for command");
        in = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            executeCommand(in.nextLine());
        }

    }

    private void executeCommand(String commandName) {
        commandName = commandName.toLowerCase();
        switch (commandName) {
            case "Help":
            case "help":
                System.out.println("Calculate: Calculate something");
                System.out.println("Help: Show this");
                System.out.println("Add: add a CashFlow (Salaire ou depense)");
                System.out.println("Clear: Delete current Data(All CashFlows)");
                System.out.println("Save: Save current Data for further use");
                System.out.println("Exit: Save data and exit current application");
                System.out.println("For details or suggestions, write to 'felix.caron2@yahoo.ca'");
                break;
            case "calculate":
            
            Extrapolator extrapol =  new Extrapolator(getfromCal(), getToCal(), DataBase.salaires, DataBase.depenses, getInitialAmount());
                System.out.println("Le " + extrapol.to.getTime().toGMTString()+" vous aurez " +extrapol.totalAtTo()+"$");
              
                break;
            case "add":
           add();
           System.out.println("Added");
            break;
        case "clear":
        DataBase.depenses.clear();
        DataBase.salaires.clear();
        System.out.println("Cleared");
        break;
        case "save":
        DataBase.save();
        System.out.println("Saved");
        break;
        case "exit":
        DataBase.save();
        System.exit(0);
        break;

            default:
           
            System.out.println("Command not recognized, verify your spelling or type 'help' for help");
                break;
        }}
    
    private void add() {
        float amount = getAmount();
        Frequence freq = getFrequence();
        Boolean type = getType();
        Calendar when = getWhen();
        if(type==Salaire){
            new Salaire(amount, when, freq);
        }else{
            new Depense(amount, when, freq);
        }
    }

    private Calendar getWhen() {
        System.out.print("Enter date of transaction 'aaaa/mm/jj' (empty is today):");
        String dataIn = in.nextLine();
        Calendar fromCal;
        if(dataIn.equals("")){
            fromCal = Calendar.getInstance();
        }else{
            try {
                String dateStr[] = dataIn.split("/");
            Builder build = new Calendar.Builder();
            build.setDate(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1])-1,Integer.parseInt(dateStr[2]));
            fromCal = build.build();
            } catch (Exception e) {
                System.out.println("Wrong input");
                fromCal = getWhen();
            }
            

        }
        return fromCal;
    }

    private Boolean getType() {
        System.out.println("Salaire  (0)");
        System.out.println("Depense  (1)");
        System.out.print("Enter type (0-1): ");
        
        String dataIn = in.nextLine();
         switch (dataIn){
            case "0":
            return Salaire;
            case "1":
            return Depense;
            default:

            return getType();
         }
        
        
    }

    private Frequence getFrequence() {
        Frequence f;
        System.out.println("Daily    (0)");
        System.out.println("Weekly   (1)");
        System.out.println("BiWeekly (2)");
        System.out.println("Monthly  (3)");
        System.out.println("Annual   (4)");
        System.out.println("Unique   (5)");
        System.out.print("Enter frequency (0-5): ");
        try{
        String dataIn = in.nextLine();
         f = Frequence.values()[Integer.parseInt(dataIn)];
        } catch (Exception e) {
            System.out.println("Wrong input");
            f = getFrequence();
        }
        return f;
    }

    private float getAmount() {
        float amount;
        try {
            System.out.print("Enter amount: ");
        String dataIn = in.nextLine();
        amount = Float.parseFloat(dataIn);
        } catch (Exception e) {
            System.out.println("Wrong input");
            amount = getInitialAmount();
        }
        
        return amount;
        
    }

    private float getInitialAmount() {
        float amount;
        try {
            System.out.print("Enter initial amount: ");
        String dataIn = in.nextLine();
        amount = Float.parseFloat(dataIn);
        } catch (Exception e) {
            System.out.println("Wrong input");
            amount = getInitialAmount();
        }
        
        return amount;
    }

    

    private Calendar getfromCal() {
        System.out.print("Enter start date 'aaaa/mm/jj' (empty is today):");
        String dataIn = in.nextLine();
        Calendar fromCal;
        if(dataIn.equals("")){
            fromCal = Calendar.getInstance();
        }else{
            try {
                String dateStr[] = dataIn.split("/");
            Builder build = new Calendar.Builder();
            build.setDate(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1])-1,Integer.parseInt(dateStr[2]));
            fromCal = build.build();
            } catch (Exception e) {
                System.out.println("Wrong input");
                fromCal = getfromCal();
            }
            

        }
        return fromCal;
    }
    private Calendar getToCal() {
        System.out.print("Enter end date 'aaaa/mm/jj' (empty is in one month):");
        String dataIn = in.nextLine();
        Calendar toCal;
        if(dataIn.equals("")){
           toCal = Calendar.getInstance();
           toCal.add(Calendar.MONTH, 1);
        }else{
            try {
                String dateStr[] = dataIn.split("/");
            Builder build = new Calendar.Builder();
            build.setDate(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1])-1,Integer.parseInt(dateStr[2]));
            toCal = build.build();
            } catch (Exception e) {
                System.out.println("Wrong input");
                toCal = getToCal();
            }
           
        }
         return toCal;
    }
}