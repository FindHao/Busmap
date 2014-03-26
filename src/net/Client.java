package net;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import core.Dijkstra;

public class Client {
	Dijkstra dijk=new Dijkstra();
	
	public Client(){
		
		
	}
	

}
/* The thread of reading data from file */
class readthefile implements Runnable{
	//store the data 
	
	public void run() {
		try {
			Scanner scan=new Scanner(new File("res/data.in"));
			
			
			
			
			
			
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file not find.");
		}
		
	}
	
}