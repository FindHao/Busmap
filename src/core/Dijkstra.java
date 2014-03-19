package core;
import java.lang.Math;

public class Dijkstra {
	final int maxn=100;
	final int maxnode=200;
	final double INFDOUBLE=0x3f3f3f3f;
	boolean gra[][];
	int routelen,nodelen;
	Route[] route;
	Node[] node;
	/*the miniest time between the two nodes*/
	double grawork1[][];
	public Dijkstra() {
		gra=new boolean[maxnode][maxnode];
		routelen=0;nodelen=0;
		route=new Route[maxn];
		node=new Node[maxnode];//?
	}
	void addRoute(Route x){
		int b;
		route[routelen++]=x;
		//follow is the method to complete the graphic
		int a=x.getPassNodelen();
		int []temppass=x.getPassNode();
		int startx=temppass[0];
		//initialize the gra to show that every node was isolated
		for(int i=0;i<maxnode;i++)
			for(int j=0;j<maxnode;j++)
				gra[i][j]=false;
		for(int i=1;i<a;i++){
			b=temppass[i];
			//calc the distance between two nodes 
			grawork1[startx][b]=Math.sqrt(Math.pow((node[startx].x-node[b].x),2)+Math.pow(node[startx].y-node[b].y, 2));
			gra[startx][temppass[i]]=true;
			startx=temppass[i];
		}
	}
	void addNode(Node x){
		node[nodelen++]=x;
	}
	/*This method is to calc the most saving time route that we needn't wait*/
	void work1(){
		
	}
	/*It's the predeal of work1*/
	
	
	
	
}
