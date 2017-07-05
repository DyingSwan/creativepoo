#include <iostream>
#include <vector>
using namespace std;

int calc(int sum,  vector<int> nums, int target){
	int find =0;	
	if(nums.empty())return find;
	int first=nums[nums.size()-1];
	nums.pop_back();
	if(sum+first ==target)find++;
	return find+ calc(sum+first,nums,target)+calc(sum,nums,target);	

}

int main(){
	int size;
	int target;
	cin>>size>>target;
	vector<int> nums;
	for(int i =0; i<size; i++){
		int tmp;
		cin>>tmp;
		nums.push_back(tmp);
	}
	cout<< calc (0,nums, target)<<endl;


	return 0;
}
