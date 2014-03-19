package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servicelmpl extends UnicastRemoteObject implements RMIService{
	protected Servicelmpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 7418384796698574511L;

	public void hello() {
		System.out.println("hello");
	}
	
}
