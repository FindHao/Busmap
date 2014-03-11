package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	ServerSocket ss=null;
	boolean started=false;
	List<Client>clients=new ArrayList<Client>();
	public void start(){
		try {
			ss=new ServerSocket(8888);
			started=true;
		}catch(BindException e){
			System.out.println("The port is being used,please close it and restart");
			System.exit(0); }
		catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while(started){
				Socket s=ss.accept();
				Client c=new Client(s);
				new Thread(c).start();
				clients.add(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Server se=new Server();
		System.out.println("Server is started.");
		se.start();
	}
	
	class Client implements Runnable{
		private Socket s;
		private DataInputStream dis=null;
		private DataOutputStream dos=null;
		private boolean beconnected=false;
		public Client(Socket s) throws IOException{
			this.s=s;
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			beconnected=true;
		}
		public void send(String str){
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {
				clients.remove(this);
				System.out.println("I'm out!");
			}
		}
		public void run() {
			String cmd;
			try {
				while(beconnected){
					cmd=dis.readUTF();
					System.out.println(cmd);
					send("hello,this is server");
				}
			} catch (EOFException e) {
				System.out.println("Client closed!");
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (s != null) {
						s.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		
		}
		}
	}
