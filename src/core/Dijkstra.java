package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


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
	/**the shortest time to return home*/
	double ansTime;
	public Dijkstra() {
		for(int i=0;i<maxnode;i++)node[i]=new Node();
		routelen=1;nodelen=1;
		for(int i=0;i<maxn;i++)route[i]=new Route();
		time1=new double[maxnode][maxnode];
		distanceTwoNodes=new double[maxnode][maxnode];
		leastTimeTrack=new int[maxnode][maxnode];
		for(int i=0;i<maxnode;i++)
			for(int j=0;j<maxnode;j++){
				gra[i][j]=false;
				time1[i][j]=INFDOUBLE;
				if(i!=j)
					distanceTwoNodes[i][j]=INFDOUBLE;
				else distanceTwoNodes[i][j]=0;
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
//			if(distanceTwoNodes[startx][b])//0 means that it didn'y be worked before
			distanceTwoNodes[startx][b]=Math.sqrt(Math.pow((node[startx].x-node[b].x),2)+Math.pow(node[startx].y-node[b].y, 2));
			if(time1[startx][b]>distanceTwoNodes[startx][b]/x.getSpeed()){
//				if(startx==9&&b==10)System.out.println("time1"+time1[startx][b]);
//				System.out.println("time  "+startx+" "+b+" "+time1[startx][b]);
				time1[startx][b]=distanceTwoNodes[startx][b]/x.getSpeed();
			time1[b][startx]=time1[startx][b];
			// if there's shorter time ,x is the better answer
		}
			route1[startx][b]=x.index;
			route1[b][startx]=x.index;
		gra[startx][temppass[i]]=true;
		gra[temppass[i]][startx]=true;
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
	public Node[] getNode(){
		return node;
	}
	/**This method is to calc the most saving time route that we needn't wait*/
	public int[] work1(int v,int destnation){
		//this is the shortest time answer
		double []timedist=new double[nodelen];
		boolean[] used=new boolean[nodelen];
		//the no of the bus you need to take on this site
		
		int[] pre=new int[nodelen];
		/**copy the information*/
		for(int i=1;i<nodelen;i++){
			timedist[i]=time1[v][i];
			used[i]=false;
			pre[i]=0;
//			System.out.println("time :"+timedist[i]);
		}
		pre[v]=v;
		timedist[v]=0;used[v]=true;
		for(int i=1;i<=nodelen;i++)if(gra[v][i])pre[i]=v;
		for(int i=1;i<nodelen;i++){
			if(i==v)continue;
			double tmp=INFDOUBLE;
			int u=v;
			/**find the nearest point u*/
			for(int j=1;j<nodelen;j++){
				
				if(!used[j] && timedist[j]<tmp){
					u=j;
					tmp=timedist[j];
				}
			}
			used[u]=true;
			for(int j=1;j<nodelen;j++){
				if(!used[j]&& time1[u][j]<1000000){
					double tmp2=timedist[u]+time1[u][j];
					if(tmp2<timedist[j]){
						timedist[j]=tmp2;pre[j]=u;
						
					}
				}
			}
		}
//		for(int i=1;i<nodelen;i++)System.out.println("pre"+pre[i]+"time"+timedist[i]);
		/**follow is to recover the way*/
		int []theSite=new int[nodelen];
		int [] passRoute=new int[nodelen];
		int i=1;
		theSite[i]=destnation;
		ansTime=timedist[destnation];
		passRoute[i++]=route1[destnation][pre[destnation]];
		int ans[]=new int[nodelen*3];
		while(pre[destnation]!=destnation){
			theSite[i]=pre[destnation];
			destnation=pre[destnation];
			passRoute[i]=route1[destnation][pre[destnation]];
			i++;
		}
		i--;
		theSite[0]=i;//the number of the point
		//ans[0]is the num of sites
		ans[0]=theSite[0];
		//ans[1..i]is the sites
		//ans[i+1..i*2-1]the No.bus
		//ans[i*2] the shortest time
		for(int j=1;j<=i;j++)ans[j]=theSite[i-j+1];
		for(int j=i+1;j<i+1+i;j++)ans[j]=passRoute[i+i-j];
		return ans;
	}
	/**To check the route no they have in common;if do,commons transmit;else if id has,id's one =common +price*/
	int []deal(int []a,int id){
		//a means the node before ,id means the index of this node .
		int[] nodepassroute=node[id].getPassRoute();
		int passLen=node[id].getLen();
		int tmpans[]=new int[nodelen];
		boolean findit;
		int minv=(int)INFDOUBLE;
		for(int i=0;i<a[0];i++)if(a[i]!=0)minv=Math.min(minv, a[i]);
		for(int j=0;j<passLen;j++){
			findit=false;
			for(int i=1;i<nodelen&&!findit;i++){
				if(nodepassroute[j]==i){
					findit=true;
					tmpans[i]=a[i];
				}
			}
			if(!findit){
				tmpans[j]=minv+route[nodepassroute[j]].getPrice();
			}
		}
		//tmpans:current node's price via this way
		return tmpans;
	}
	public void work2(int v,int destnation){
		boolean used[]=new boolean [nodelen];
		//every site pass this route's cost
		int price[][]=new int[nodelen][routelen];
		
		int pre[]=new int[nodelen];
		for(int i=1;i<nodelen;i++){
			used[i]=false;
			pre[i]=0;
		}
		used[v]=true;
		pre[v]=v;
		for(int i=1;i<=nodelen;i++)if(gra[v][i])pre[i]=v;
		Queue<Integer>que=new LinkedList<Integer>();
		que.offer(v);
		price[v][0]=node[v].getLen();
		for(int i=1;i<=price[v][0];i++)price[v][i]=route[node[v].getPassRoute()[i-1]].price;
		while(!que.isEmpty()){
			int u=que.poll();
			for(int i=1;i<nodelen;i++)
				if(gra[u][i]&&!used[i]){
					//the neighborhoods
					que.offer(i);
					used[i]=true;
					price[i]=deal(price[u],i);
				}
		}
		int minv=1000;
		for(int j=1;j<routelen;j++)
		System.out.println("price"+price[destnation][j]+"    ");
		
	}
	public double getAnsTime(){
		return ansTime;
	}
	
	/**This is the thread to read the inputdata*/
	class readthefile implements Runnable{
		public void run() {
			try {
				int n,tmppassnode;
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
				Scanner scan2=new Scanner(new File("res/data.in"));
				while(scan2.hasNext()){
					Route b=new Route();
					b.index=scan2.nextInt();
					b.len=scan2.nextInt();
					n=b.len;
					for(int j=0;j<n;j++){
						tmppassnode=scan2.nextInt();
						b.addPassNode(tmppassnode);
						node[tmppassnode].addPassRoute(b.index);
					}
					b.price=scan2.nextInt();
					b.time=scan2.nextInt();
					b.speed=scan2.nextDouble();
//					System.out.println(b.speed);
					addRoute(b);
				}
				scan2.close();
				for(int i=0;i<nodelen;i++){
					for(int j=0;j<nodelen;j++){
						if(i==j)time1[i][j]=0;
//						System.out.print(new DecimalFormat("000.00").format(time1[i][j])+" ");
					}
//					System.out.println();
				}
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
//	}
	
}
