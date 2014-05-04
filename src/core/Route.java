package core;

public class Route {
	  double speed;
	 int index;
	 int price;
	 int time;
	 int len;
	 int passNode[]=new int[100];
	public void addPassNode(int x){
		passNode[len++]=x;
	}
	public int getPassNodelen(){
		return len;
	}
	/**Nodes passed*/
	public int [] getPassNode(){
		return passNode;
	}
	public double getSpeed(){
		return speed;
	}

}
