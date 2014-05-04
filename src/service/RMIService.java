package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {
		public void hello() throws RemoteException;
}
