#include <stdio.h>

int tmp = 0;
int result = -1;
int count = 0;
int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };

void change(int room[][8], int n, int m, int now_x, int now_y)
{
	if (now_x == -1 || now_x == n || now_y == -1 || now_y == m)
		return;
	else
	{
		for (int k = 0; k < 4; k++)
		{
			if (room[now_x + dx[k]][now_y + dy[k]] == 0)
			{
				room[now_x + dx[k]][now_y + dy[k]] = 2;
				change(room, n, m, now_x + dx[k], now_y + dy[k]);
			}
		}
	}
}

void plague(int room[][8], int n, int m)
{
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			if (room[i][j] == 2)
				change(room, n, m, i, j);

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			if (room[i][j] == 0)
				tmp++;


	if (tmp > result)
		result = tmp;
	tmp = 0;
}

void def(int room[][8], int n, int m)
{
	
}

int main()
{
	int n, m;
	scanf_s("%d%d", &n, &m);

	int room[8][8];

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			scanf_s("%d", &room[i][j]);

	
	printf("%d\n", result);
	
	return 0;
}



