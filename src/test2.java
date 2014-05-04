
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import core.Dijkstra;

public class test2{

	public static void main(String[] args) throws FileNotFoundException {
		new Dijkstra();
//		test();
	}
	static void test() throws FileNotFoundException{
		
		Scanner in=new Scanner(new File("res/hello.txt"));
		while(in.hasNext()){
			System.out.println(in.nextInt());
		}
		in.close();
	}
}
