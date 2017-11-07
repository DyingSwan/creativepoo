#include <iostream>
#include <cstdio>
#include <queue>

using namespace std;
int N,M;
int map[8][8];
int c[8][8];
int ax[4] = {0,-1,0,1};
int ay[4] = {1,0,-1,0};
queue<pair<int,int> > q;
int maxNum = -1;

bool isNotOut(int x,int y)
{
    if(0 <= x && x < N && 0 <= y && y < M)
        return true;
    return false;
}

void backup(int (*origin)[8], int (*copy)[8])
{
    
    for(int i = 0 ; i < 8 ; ++i)
    {
        for(int j = 0 ; j < 8 ; ++j)
        {
            copy[i][j] = origin[i][j];
        }
    }   
}
void reload(int (*origin)[8], int (*copy)[8])
{
    
    for(int i = 0 ; i < 8 ; ++i)
    {
        for(int j = 0 ; j < 8 ; ++j)
        {
            origin[i][j] = copy[i][j];
        }
    }
}
void bfs() // 병 발발
{
    int x,y,nx,ny;

    while(!q.empty())
    {
        x = q.front().first;
        y = q.front().second;
        q.pop();

        for(int i = 0 ; i < 4; ++i)
        {
            nx = x + ax[i];
            ny = y + ay[i];

            if(isNotOut(nx,ny) && map[nx][ny] == 0)
            {
                map[nx][ny] = 2;
                q.push(make_pair(nx,ny));
            }
        }
    }
}

void _count()
{
    // 0인 곳 세기
    int cnt = 0;
    for(int i = 0 ; i < N; ++i)
    {
        for(int j = 0 ; j < M; ++j)
        {
            if(map[i][j] == 0)
            {
                cnt++;
            }
        }
    }
    if(cnt > maxNum) 
    {
        maxNum = cnt;
    }
}

void dfs(int x , int y ,int count)
{// 벽 세우기

        map[x][y] = 1;
        c[x][y] = 1;
        int tmp[8][8];
     
        if(count == 3)
        {
            
            backup(map,tmp);
            for(int i = 0 ; i < N ; ++i)
            {
                for(int j = 0 ; j < M ; ++j)
                {
                    if(map[i][j] == 2)
                    {
                        q.push(make_pair(i,j));
                    }
                }
            }

            bfs();

            _count();
            
            reload(map,tmp);
     
            c[x][y] = 0;
            map[x][y] = 0;
            return;
        }
     
        for(int i = x; i < N; i++)
        {
            for(int j = 0; j < M; j++)
            {
                if(c[i][j]==1 || map[i][j] != 0) continue;
                dfs(i, j, count + 1);
            }
        }
     
        map[x][y] = 0;
        c[x][y] = 0;
    
}

int main()
{
    scanf("%d %d",&N,&M);

    for(int i = 0 ; i < N; ++i)
    {
        for(int j = 0 ; j < M; ++j)
        {
            scanf("%d",&map[i][j]);
        }
    }
    int flag = 0;

    for(int i = 0 ; i < N; ++i)
    {
        for(int j = 0 ; j < M; ++j)
        {
            if(map[i][j] == 0)
            {
                dfs(i,j,1);
                
            }
            
        }
    }
    

    printf("%d\n",maxNum);
}