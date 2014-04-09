package core;

public class Route {
	double speed;
	int index;
	int price;
	int time;
	int len;
	int passNode[];
	Route(int id,int time1,int price1,double speed1){
		index=id;speed=speed1;
		time=time1;price=price1;
		len=0;
	}
	void addpassRoute(int x){
		passNode[len++]=x;
	}
	int getPassNodelen(){
		return len;
	}
	/**Nodes passed*/
	int [] getPassNode(){
		return passNode;
	}
	double getSpeed(){
		return speed;
	}
}
