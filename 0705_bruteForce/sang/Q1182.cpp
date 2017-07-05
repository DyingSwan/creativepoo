#include <iostream>
#include <cstdio>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;

int N,S;

void dfs(int idx, int sum,int& cnt,int* p)
{
        if (idx >= N) {
                if (sum == S) cnt++;
                return;
        }

        dfs(idx + 1, sum, cnt, p);
        dfs(idx + 1, sum + p[idx], cnt, p);
}


int main(int argc, char const *argv[]) {


        cin >> N >> S;

        int* p;
        p = new int[N];

        for(int i=0; i<N; ++i)
        {
                cin >> p[i];
        }

        int sum = 0,cnt = 0;

        dfs(1, 0, cnt, p);
        dfs(1, p[0], cnt, p);

        if (S == 0) --cnt;

        cout << cnt << endl;

        return 0;
}
