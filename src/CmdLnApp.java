import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Scanner;

public class CmdLnApp {
    private static final boolean typeSalary = true;
    private static final boolean typeCashOut = false;
    Scanner scanner;

    public static void launch() {
        new CmdLnApp();

    }

    CmdLnApp() {
        System.out.println("App is started, waiting for command");
        scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            executeCommand(scanner.nextLine());
        }

    }

    private void executeCommand(String commandName) {
        commandName = commandName.toLowerCase();
        switch (commandName) {
            //Show help dialog
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

                //next methods are defined separately in cmdln and UI
                //calculates the amount in account at the end of date A from beginning of date B at C$ in accounts
            case "calculate":
                Extrapolator extrapol =  new Extrapolator(getfromCal(), getToCal(), DataBase.salarys, DataBase.cashOuts, getInitialAmount());
                System.out.println("On " + extrapol.to.getTime().toGMTString()+" you will have " +extrapol.totalAtTo()+"$");
              
                break;

                //creates a new cashflow and sets it up
            case "add":
           add();
           System.out.println("Added");
            break;

            //for next methods, they are centralized in main(see main for further details)
            
        case "clear":
        if(Main.clearAll()){
            System.out.println("Cleared");}
        else{
            System.out.println("There was an error clearing data");
        }
        break;
        case "save":
        if(Main.save()){
            System.out.println("Saved");
        }else{
            System.out.println("There was an error, your content might not be saved");
        }
        
        break;
        case "exit":
       Main.exit();
       System.out.println("There was an error, this should really not happen... ");
        break;

            default:
           
            System.out.println("Command not recognized, verify your spelling or type 'help' for help");
                break;
        }}
    
    private void add() {
        float amount = getAmount();
        Frequency freq = getFrequence();
        Boolean type = getType();
        Calendar when = getWhen();
        if(type==typeSalary){
            new Salary(amount, when, freq);
        }else{
            new CashOut(amount, when, freq);
        }
    }

    private Calendar getWhen() {
        System.out.print("Enter date of transaction 'aaaa/mm/jj' (empty is today):");
        String dataIn = scanner.nextLine();
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
        
        String dataIn = scanner.nextLine();
         switch (dataIn){
            case "0":
            return typeSalary;
            case "1":
            return typeCashOut;
            default:

            return getType();
         }
        
        
    }

    private Frequency getFrequence() {
        Frequency f;
        System.out.println("Daily    (0)");
        System.out.println("Weekly   (1)");
        System.out.println("BiWeekly (2)");
        System.out.println("Monthly  (3)");
        System.out.println("Annual   (4)");
        System.out.println("Unique   (5)");
        System.out.print("Enter frequency (0-5): ");
        try{
        String dataIn = scanner.nextLine();
         f = Frequency.values()[Integer.parseInt(dataIn)];
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
        String dataIn = scanner.nextLine();
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
        String dataIn = scanner.nextLine();
        amount = Float.parseFloat(dataIn);
        } catch (Exception e) {
            System.out.println("Wrong input");
            amount = getInitialAmount();
        }
        
        return amount;
    }

    

    private Calendar getfromCal() {
        System.out.print("Enter start date 'aaaa/mm/jj' (empty is today):");
        String dataIn = scanner.nextLine();
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
        String dataIn = scanner.nextLine();
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