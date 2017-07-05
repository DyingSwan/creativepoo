#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;
int data[1001];
vector<int> dist;
int calcLen(int size){
	if(size==1)return 1;
	int max =0;
	for(int i=0; i< size; i++){
		if(data[i]<data[size-1]){
			if(dist[i]>max)max=dist[i];
		}
	}
	return 1+ max;
}
int main(){
	int size;
	cin>>size;
	for(int i =0; i< size; i++){
		cin>>data[i];
	}
	for (int i =0; i<size; i++){
		dist.push_back( calcLen(i+1));
	}
	sort(dist.begin(),dist.end());
	cout<<dist[dist.size()-1]<<endl;
	return 0;
}
