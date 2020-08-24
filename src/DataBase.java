import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataBase implements Serializable{
static ArrayList<Salary> salarys = new ArrayList<Salary>();
static ArrayList<CashOut> cashOuts = new ArrayList<CashOut>();
public static void save() {
	try {
		FileOutputStream fos = new FileOutputStream(new File("Data"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(cashOuts);
		oos.writeObject(salarys);
		oos.close();
		fos.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void load() {
	try {
		FileInputStream fos = new FileInputStream("Data");
		ObjectInputStream oos = new ObjectInputStream(fos);
		cashOuts=(ArrayList<CashOut>) oos.readObject();
		salarys=(ArrayList<Salary>) oos.readObject();
		oos.close();
		fos.close();
	} catch (IOException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
