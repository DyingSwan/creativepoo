#include<iostream>

using namespace std;
long long int d[91];
int main(){
	int line;
	cin>>line;
	d[0]=d[1]=1;
	for(int i =2; i< line; i++){
		d[i]=d[i-1]+d[i-2];	
	}
	cout<<d[line-1]<<endl;
	return 0;

}
