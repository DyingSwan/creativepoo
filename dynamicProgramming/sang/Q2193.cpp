#include <iostream>
#include <cstdio>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;
// step1. 구하고자 하는 것 문장으로 만들기.
// d[i][j] = i자리 이친수에서 0,1로 끝나는 것의 갯수

long d[90][2];
long finary(int n)
{

        // type1. Bottom-Up 방식.
        // step2. 식 세우기.
        d[1][0] = 0;
        d[1][1] = 1;
        for(int i =2; i<=n; i++)
        {
                // case1. end is 0.
                d[i][0] = d[i-1][0] + d[i-1][1];
                // case2. end is 1.
                d[i][1] = d[i-1][0];
        }
        return d[n][0] + d[n][1];


}
int main(int argc, char const *argv[]) {

        int n;
        cin >> n;
        cout << finary(n) << endl;
        return 0;
}
