import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class test {
	public static void main(String[] args) {
		try {
			Scanner scan=new Scanner(new File("res/da.txt"));
			System.out.println(scan.nextLine());
			scan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
