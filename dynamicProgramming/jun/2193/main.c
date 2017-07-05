//설명 참고: http://blog.naver.com/occidere/220788046159 
#include<stdio.h>
int main(){
    int i,n; scanf("%d",&n);
    long long int dp[91] = {0, 1};
    for(i=2;i<=n;i++) dp[i] = dp[i-1]+dp[i-2];
    printf("%lld", dp[n]);
}
