#include <iostream>
#include <cstdio>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;
// step1. 구하고자 하는 것 문장으로 만들기.
// d[n] = 'a[i]를 마지막으로 하는' 가장 긴 증가하는 부분 수열.
// a[i]= 수열의 i 번째 원소.
int d[1000];
int a[1000];
int seq(int n)
{

        // type1. Bottom-Up 방식.
        // step2. 식 세우기.
        // 여기서는 딱 써지는 수식이 없다. 상황을 해석해야 한다.
        for(int i = 0; i<n; i++)
        {
                d[i] = 1;
                for(int j = 0; j<i; j++)//앞에 있으면서
                {
                        if(a[j] < a[i] && d[i] < d[j]+1)
                        {// 작으면서 가장 긴 거
                                d[i] = d[j] + 1;
                        }
                }
        }


        int max = d[0];
        for(int i = 1; i<n; i++)
        {
                if(d[i] > max) max = d[i];
        }
        return max;

}
int main(int argc, char const *argv[]) {

        int n;
        cin >> n;
        for(int i=0; i<n; i++)
        {
                cin >> a[i];
        }
        cout << seq(n) << endl;
        return 0;
}
