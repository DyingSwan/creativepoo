#include<stdio.h>
int main(){
    int i,j,n,d[1000],a[1000],max,len;
    for(scanf("%d",&n),max=i=1;i<=n;i++){
        for(scanf("%d",&a[i]),len=j=0;j<i;j++)
            if(a[j]<a[i]&&len<d[j]) len=d[j];
        if(max<(d[i]=len+1)) max=d[i];
    }
    printf("%d",max);
}
