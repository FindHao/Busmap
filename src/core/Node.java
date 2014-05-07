package core;

public class Node {
	 final int  maxn=100;
	 int index;
	 int passRoute[];
	 int len;
	 int x,y;
	 String name;
	public Node(){
		index=0;len=0;x=y=0;
		len=0;
		passRoute=new int[maxn];
	}
	//just node[id].addPassRoute
	public void addPassRoute(int x){passRoute[len++]=x;	}
	public int getLen(){return len;	}
	public int [] getPassRoute(){return passRoute;	}
	public int getIndex(){return index;	}
	public void setName(String aname){name=aname;	}
	public void setIndex(int id){index=id;	}
	public String getName(){return name;	}
	public int getX(){return x;}
	public int getY(){return y;}
}
