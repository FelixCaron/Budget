public class Main {
public static void main(String[] args) {
    DataBase.load();
CmdLnApp.launch();
}



//add and calculate methods need to be defined in UI AND in cmdln SEPARATELY because it requires user entries
//here are functions accessed by cmdln AND UI


//clears the data contained in DATABASE
public static boolean clearAll(){
    try {
    DataBase.cashOuts.clear();
    DataBase.salarys.clear();
        return true;
    } catch (Exception e) {
       return false;
    }
    
}



//Saves every added element in Database
public static boolean save() {
    try {
        DataBase.save();
        return true;
    } catch (Exception e) {
        return false;
    }
    
	
}



//Saves and exit the app
public static void exit() {
    if(save()){
        System.exit(0);
    }else{
        System.out.println("There was an error.. Should not happen (Main.exit())");
        if(save()){
            System.exit(0);
        }
    }
}
}