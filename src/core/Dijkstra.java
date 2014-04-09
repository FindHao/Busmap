package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.Scanner;


public class Dijkstra {
	final int maxn=100;
	final int maxnode=200;
	final double INFDOUBLE=0x3f3f3f3f;
	/**true:i toj have a path*/
	boolean gra[][];
	int routelen,nodelen;
	Route[] route;
	Node[] node;
	/**the distance between two nodes*/
	double distanceTwoNodes[][];
	/**to mark the car index*/
	int route1[][];
	/**the miniest time between two nodes*/
	double time1[][];
	/**the least time by this way*/
	int leastTimeTrack[][];
	public Dijkstra() {
		gra=new boolean[maxnode][maxnode];
		routelen=0;nodelen=0;
		route=new Route[maxn];
		node=new Node[maxnode];//?
		time1=new double[maxnode][maxnode];
		distanceTwoNodes=new double[maxnode][maxnode];
		leastTimeTrack=new int[maxnode][maxnode];
		for(int i=0;i<maxnode;i++)
			for(int j=0;j<maxnode;j++){
				gra[i][j]=false;
				time1[i][j]=INFDOUBLE;
				distanceTwoNodes[i][j]=INFDOUBLE;
			}
	}
	/** It's in the input process*/
	void addRoute(Route x){
		int b;
		route[routelen++]=x;
		//a nodes this route owning
		int a=x.getPassNodelen();
		int []temppass=x.getPassNode();
		int startx=temppass[0];
		for(int i=1;i<a;i++){
			b=temppass[i];
			//calc the distance between two nodes 
			if(distanceTwoNodes[startx][b]!=0)
				distanceTwoNodes[startx][b]=Math.sqrt(Math.pow((node[startx].x-node[b].x),2)+Math.pow(node[startx].y-node[b].y, 2));
			if(time1[startx][b]>distanceTwoNodes[startx][b]/x.getSpeed()){
				time1[startx][b]=distanceTwoNodes[startx][b]/x.getSpeed();
			}
			gra[startx][temppass[i]]=true;
			startx=temppass[i];
		}
	}
	boolean deng(double a,double b){
		if(Math.abs(a-b)<=Math.pow(1, -5))return true;
		else return false;
		
	}
	void addNode(Node x){
		node[nodelen++]=x;
	}
	/**This method is to calc the most saving time route that we needn't wait*/
	void work1(int v){
		double []timedist=new double[maxnode];
		boolean[] used=new boolean[maxnode];
		int[] pre=new int[maxnode];
		for(int i=0;i<maxnode;i++){
			timedist[i]=time1[v][i];
			used[i]=false;
			pre[i]=(gra[v][i])?0:v;
		}
		timedist[v]=0;used[v]=true;
		//it begins from 0
		for(int i=0;i<maxnode;i++){
			if(i==v)continue;
			double tmp=INFDOUBLE;
			int u=v;
			for(int j=0;j<maxnode;j++){
				if(!used[j] && timedist[j]<tmp){
					u=j;
					tmp=timedist[j];
				}
			}
			used[u]=true;
			for(int j=0;j<maxnode;j++){
				if(!used[j] && gra[u][j]){
					double tmp2=timedist[u]+time1[u][j];
					if(tmp2<timedist[j]){
						timedist[j]=tmp2;pre[j]=u;
					}
				}
			}
		}
	}
	class readthefile implements Runnable{
		//store the data 
		
		public void run() {
			try {
				Scanner scan=new Scanner(new File("res/data.in"));
				int i=0,n;
				while(scan.hasNext()){
					route[i].index=scan.nextInt();
					n=scan.nextInt();
					for(int j=0;j<n;j++){
						
					}
				}
				
				
				
				
				
				scan.close();
			} catch (FileNotFoundException e) {
				System.out.println("The file not find.");
			}
			
		}
		
	}
	
	
	
}
