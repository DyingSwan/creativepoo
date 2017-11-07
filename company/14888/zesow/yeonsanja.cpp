#include <iostream>
#include <cstdio>
#include <vector>
#include <climits>
#include <algorithm>
using namespace std;

int N;
int A[11];
int y[4];
vector<int> v;

int main()
{
    scanf("%d",&N);
    for(int i = 0 ; i < N ; ++i)
    {
        scanf("%d",&A[i]);
    }

    for(int i = 0 ; i < 4;++i)
    {
        scanf("%d",&y[i]);
    }

    for(int i = 0 ; i < 4; ++i)
    {
        for(int j = 0; j < y[i]; ++j)
        {
            if(i==0)v.push_back(0);
            else if(i == 1)v.push_back(1);
            else if(i==2)v.push_back(2);
            else v.push_back(3);
        }
    }
    int result;
    int min = INT_MAX;
    int max = INT_MIN;
    do {
        result = 0;
        if(v[0]==0)
        {
            result = A[0]+A[1];
        }
        else if(v[0]==1)
        {
            result = A[0]-A[1];
        }
        else if(v[0]==2)
        {
            result = A[0]*A[1];
        }
        else{
            result = A[0] / A[1];
        }

        for(int i = 2 ; i < N; ++i)
        {
            if(v[i-1] == 0) result += A[i];
            else if(v[i-1] == 1) result -= A[i];
            else if(v[i-1] == 2) result *= A[i];
            else result /= A[i];   
        }
        if(result > max) max = result;
        if(result < min) min =result;

      } while ( next_permutation(v.begin(),v.end()));

      printf("%d\n%d\n",max,min);
}