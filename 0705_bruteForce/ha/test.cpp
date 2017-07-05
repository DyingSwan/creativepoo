#include<iostream>
#include<vector>
using namespace std;

int main(){
	vector<int> test;
	test.push_back(1);
	test.push_back(1);
	test.push_back(1);
	test.push_back(1);
	for(int i =0; i<4; i++){
	cout<< test[i]<<endl;
	}
	int k=test[test.size()-1];
	cout<<"pls"<<k<<endl;
	for(int i=0; i<test.size(); i++){
	cout<<test[i]<<endl;	
	}
	return 0;
}
