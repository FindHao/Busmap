package core;

public class Node {
	final int  maxn=100;
	int index;
	int passRoute[];
	int len;
	int x,y;
	Node(int id,int x1,int y1){
		index=id;x=x1;y=y1;
		len=0;
		passRoute=new int[maxn];
	}
	//just node[id].addPassRoute
	void addPassRoute(int x){
		passRoute[len++]=x;
	}
	int [] getPassRoute(){
		return passRoute;
	}
	int getIndex(){
		return index;
	}
}
