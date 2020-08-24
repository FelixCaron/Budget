import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataBase implements Serializable{
static ArrayList<Salaire> salaires = new ArrayList<Salaire>();
static ArrayList<Depense> depenses = new ArrayList<Depense>();
public static void save() {
	try {
		FileOutputStream fos = new FileOutputStream(new File("Data"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(depenses);
		oos.writeObject(salaires);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void load() {
	try {
		FileInputStream fos = new FileInputStream("Data");
		ObjectInputStream oos = new ObjectInputStream(fos);
		depenses=(ArrayList<Depense>) oos.readObject();
		salaires=(ArrayList<Salaire>) oos.readObject();
		oos.close();
		fos.close();
	} catch (IOException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
