#include<stdio.h>

int n, m, map[101][101];
const int DX[4]={-1,1,0,0}, DY[4]={0,0,-1,1};

int allMelted(){
    int i, j, count = 0;
    for(i=0;i<n;i++)
        for(j=0;j<m;j++){
            if(map[i][j]==1 || map[i][j]==2){
                map[i][j] = 1;
                count++;
            }
            else map[i][j] = 0;
        }
    return count;
}

int isInRange(int x, int y){
    return (0<=x&&x<n)&&(0<=y&&y<m);
}

void dfs(int x, int y){
    int i, ax, ay;
    map[x][y] = -1;

    for(i=0;i<4;i++){
        ax=x+DX[i]; ay=y+DY[i];

        if(isInRange(ax, ay)){
            if(map[ax][ay]>0) map[ax][ay]++;
            else if(map[ax][ay]==0) dfs(ax, ay);
        }

    }
}

int main(){
    int i, j, k; scanf("%d%d", &n, &m);

    for(i=0;i<n;i++)
        for(j=0;j<m;j++){
            scanf("%d", &k);
            map[i][j] = k;
        }

    for(k=0;allMelted();k++) dfs(0, 0); 

    printf("%d", k);
}
