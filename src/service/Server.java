package service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
public static void main(String[] args) {
	try {
		Servicelmpl service=new Servicelmpl();
		LocateRegistry.createRegistry(8888);
		Naming.rebind("rmi://localhost:8888/service",service);
		System.out.println("Service started!");
		
	} catch (RemoteException e) {
		e.printStackTrace();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
