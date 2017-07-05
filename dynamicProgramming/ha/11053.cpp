#include<iostream>
using namespace std;
int data[1001];
int d[1001];
int findNext(int n){
	if(n==1) return 1;
	int max= 0;
	for(int i =0; i<n; i++){
		if(data[i]<data[n-1]&&data[i]>data[max])max=i;
		
	}
	return 1+ findNext(max+1);

}
int main(){
	d[0]=1;
	int n;
	cin>>n;
	for(int i =0; i< n; i++){
		cin>>data[i];
	}
	for(int i =0; i< n ; i++){
		d[i]=findNext(i+1);
	}
	cout<<d[n-1]<<endl;
	
	return 0;
}
