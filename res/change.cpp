#include <cstdio>

using namespace std;

char  site[100][100];
int data[100][2];
int main(){
	freopen("E:/Yun/Code/GitHub/Busmap/res/p.txt","r",stdin);
	freopen("E:/Yun/Code/GitHub/Busmap/res/site1.txt","w",stdout);
	int x,i=0;
	
	while(scanf("%d %s",&x,site[i])!=EOF){
		i++;
	}
	fclose(stdin);
	freopen("E:/Yun/Code/GitHub/Busmap/res/site1.in","r",stdin);
	int ii=0;
	while(scanf("%d%d%d",&x,&data[ii][0],&data[ii][1])!=EOF){ii++;};
	for(int j=0;j<66;j++){
		printf("%d %s %d %d\n",j+1,site[j],data[j][0],data[j][1]);
	}
		fclose(stdin);
	fclose(stdout);

	return 0;
}