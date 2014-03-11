package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Client {
	Socket s=null;
	DataOutputStream dos=null;
	DataInputStream dis=null;
	boolean connected=false;
	Thread rev=new Thread(new Revthread());
	public static void main(String[] args) {
		Client c=new Client();
		c.connect();
		try {
			c.send("hello,this is client.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void connect(){
		try {
			s=new Socket("localhost",8888);
			dis=new DataInputStream(s.getInputStream());
			dos=new DataOutputStream(s.getOutputStream());
			System.out.println("Client logged");
			connected=true;
			rev.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String cmd) throws IOException{
		dos.writeUTF(cmd);
		dos.flush();
	}
	public void disconnect(){
		try {
			dis.close();dos.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
public class  Revthread implements Runnable{

	public void run() {
		String show=null; 
		while(connected){
			try {
				show = dis.readUTF();
				System.out.println(show);
			}catch(SocketException e){
				System.out.println("out");}
				catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
		
	}
}
