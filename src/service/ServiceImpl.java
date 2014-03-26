package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements RMIService{
	protected ServiceImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 7418384796698574511L;

	public void hello() {
		System.out.println("hello");
	}
	
}
