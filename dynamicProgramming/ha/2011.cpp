#include<iostream>
#include<vector>

using namespace std;
vector<int> code;
int d[5001];
int decode(int n){
	if(n==1)return 1;
	if(n==2&&code[0]==0)return 1;
	if(code[code.size()-1]==2&&(0<=code[code.size()-2]&&code[code.size()-2]<=6)){
		return decode(n-1)%10000000+decode(n-2)&10000000;
	}
	if(code[code.size()-1]==1&&(0<=code[code.size()-2]&&code[code.size()-2]<=9)){
		return decode(n-1)%10000000+decode(n-2)%10000000;
	}
	return decode(n-1)%10000000;
}

int main(){
	int num;
	cin>>num;
	while(num>0){
		code.push_back(num%10);
		num/=10;
	}
	for(int i=0; i< code.size(); i++){
	cout<<code[i]; 
	}
	cout<<decode(code.size())<<endl;
	return 0;
}
