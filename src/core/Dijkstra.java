package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.Scanner;

import javax.annotation.PreDestroy;


public class Dijkstra {
	final int maxn=100;
	final int maxnode=200;
	final double INFDOUBLE=0x3f3f3f3f;
	/**true:i toj have a path*/
	boolean gra[][]=new boolean[maxnode][maxnode];;
	public int routelen,nodelen;
	public Route[] route=new Route[maxn];
	public Node[] node=new Node[maxnode];
	/**the distance between two nodes*/
	double distanceTwoNodes[][];
	/**to mark the route  route1: the index you need to take when you want the shortest time from i to j*/
	int route1[][]=new int[maxnode][maxnode];
	/**the miniest time between two nodes*/
	double time1[][];
	/**the least time by this way*/
	int leastTimeTrack[][];
	public Dijkstra() {
		for(int i=0;i<maxnode;i++)node[i]=new Node();
		routelen=0;nodelen=0;
		for(int i=0;i<maxn;i++)route[i]=new Route();
		time1=new double[maxnode][maxnode];
		distanceTwoNodes=new double[maxnode][maxnode];
		leastTimeTrack=new int[maxnode][maxnode];
		for(int i=0;i<maxnode;i++)
			for(int j=0;j<maxnode;j++){
				gra[i][j]=false;
				time1[i][j]=INFDOUBLE;
				distanceTwoNodes[i][j]=INFDOUBLE;
			}
//		inputget();
		readthefile in=new readthefile();
		in.run();
		
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
			if(distanceTwoNodes[startx][b]!=0)//0 means that it didn'y be worked before
				distanceTwoNodes[startx][b]=Math.sqrt(Math.pow((node[startx].x-node[b].x),2)+Math.pow(node[startx].y-node[b].y, 2));
			if(time1[startx][b]>distanceTwoNodes[startx][b]/x.getSpeed()){
				time1[startx][b]=distanceTwoNodes[startx][b]/x.getSpeed();
				// if there's shorter time ,x is the better answer
				route1[startx][b]=x.index;
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
	public int[] work1(int v,int destnation){
		/**this is the shortest time answer*/
		double []timedist=new double[nodelen];
		boolean[] used=new boolean[nodelen];
		int [] passRoute=new int[nodelen];
		int[] pre=new int[nodelen];
		/**copy the information*/
		for(int i=0;i<nodelen;i++){
			timedist[i]=time1[v][i];
			used[i]=false;
			pre[i]=(gra[v][i])?v:i;//mark the pre point
		}
		timedist[v]=0;used[v]=true;
		for(int i=0;i<nodelen;i++){
			if(i==v)continue;
			double tmp=INFDOUBLE;
			int u=v;
			/**find the nearest point u*/
			for(int j=0;j<nodelen;j++){
				if(!used[j] && timedist[j]<tmp){
					u=j;
					tmp=timedist[j];
				}
			}
			used[u]=true;
			pre[u]=v;
			
			for(int j=0;j<nodelen;j++){
				if(!used[j] && gra[u][j]){
					double tmp2=timedist[u]+time1[u][j];
					if(tmp2<timedist[j]){
						timedist[j]=tmp2;pre[j]=u;
					}
				}
			}
		}
		/**follow is to recover the way*/
		int []theWayPoint=new int[nodelen];
		int i=1;
		while(pre[destnation]!=destnation){
			theWayPoint[i++]=pre[destnation];
			destnation=pre[destnation];
			passRoute[i]=route1[destnation][pre[destnation]];
		}
		theWayPoint[0]=i;//the number of the point
		return theWayPoint;
	}
	
	
	/**This is the thread to read the inputdata*/
	class readthefile implements Runnable{
		public void run() {
			try {
				int i=0,n,tmppassnode;
				Scanner scan=new Scanner(new File("res/sites.txt"));
				while(scan.hasNext()){
					Node a=new Node();//here !!!!Attention:must be new every time
					a.index=scan.nextInt();
					a.setName(scan.next());
					a.x=scan.nextInt();
					a.y=scan.nextInt();
					addNode(a);
				}
				scan.close();
				i=0;
				Scanner scan2=new Scanner(new File("res/data.in"));
				Route b=new Route();
				while(scan2.hasNext()){
					b.index=scan2.nextInt();
					b.len=scan2.nextInt();
					n=b.len;
					for(int j=0;j<n;j++){
						tmppassnode=scan2.nextInt();
						b.addPassNode(tmppassnode);
						node[tmppassnode].addPassRoute(route[i].index);
					}
					b.price=scan2.nextInt();
					b.time=scan2.nextInt();
					b.speed=scan2.nextDouble();
					addRoute(b);
				}
				scan2.close();
			} catch (FileNotFoundException e) {
				System.out.println("The file not find.");
			}
			
			
		}
		
	}
	
	
//	void putput(){
//		for(int i=0;i<nodelen;i++){
//System.out.println(node[i].getName());
//		}
//	}
//	public static void main(String[] args) {
//		new Dijkstra();
//	}
	
}
