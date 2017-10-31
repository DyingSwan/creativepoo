
#include <iostream>
using namespace std;


class Graph
{
	int n, m;  // 세로 n , 가로 m
	int **room;  // 동적 2차원 배열 room
	int cleanCount;  // 청소한 방의 개수 count
	bool res;  // 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다. true 이면 종료
public:
	Graph(int n, int m);
	~Graph();
	void inputMatrix(int n, int m);  // matrix를 입력 받는 함수
	void clean(int r, int c, int d);  // clean 실행 함수
	int dirNorth(int r, int c);  // 쳐다보는 방향이 북쪽인 경우에서 네 방향을 체크하는 함수
	int dirSouth(int r, int c);  // 쳐다보는 방향이 남쪽인 경우에서 네 방향을 체크하는 함수
	int dirEast(int r, int c);  // 쳐다보는 방향이 동쪽인 경우에서 네 방향을 체크하는 함수
	int dirWest(int r, int c);  // 쳐다보는 방향이 서쪽인 경우에서 네 방향을 체크하는 함수
	void printCleanCount();  // 결과를 출력하는 함수.
};

Graph::Graph(int n, int m)
{
	this->n = n;
	this->m = m;
	room = new int *[n];
	for (int i = 0; i < n; i++)
		room[i] = new int[m];
	cleanCount = 0;
	res = false;
}

Graph::~Graph()
{
	for (int i = 0; i < n; i++)
		delete[] room[i];
	delete[] room;
}

void Graph::printCleanCount()
{
	cout << cleanCount << endl;
}

void Graph::inputMatrix(int n, int m)
{
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			cin >> room[i][j];
}

int Graph::dirEast(int r, int c)
{
	if (r - 1 >= 0 && room[r - 1][c] == 0)  // 북쪽이 0이면 
		return 0;
	else if (c - 1 >= 0 && room[r][c - 1] == 0) // 서쪽이 0이면
		return 3;
	else if (r + 1 < n && room[r + 1][c] == 0) // 남쪽이 0이면
		return 2;
	else if (c + 1 < m && room[r][c + 1] == 0) // 동쪽이 0이면
		return 1;
	else if (c - 1 >= 0 && room[r][c - 1] != 1)  // 뒤로 갈 수 있으면 10을 반환
		return 10;
	else  // 아무 방향으로도 가지 못하면 99 반환
		return 99;
}

int Graph::dirWest(int r, int c)
{
	if (r + 1 < n && room[r + 1][c] == 0) // 남쪽이 0이면
		return 2;
	else if (c + 1 < m && room[r][c + 1] == 0) // 동쪽이 0이면
		return 1;
	else if (r - 1 >= 0 && room[r - 1][c] == 0)  // 북쪽이 0이면
		return 0;
	else if (c - 1 >= 0 && room[r][c - 1] == 0) // 서쪽이 0이면
		return 3;
	else if (c + 1 < m && room[r][c + 1] != 1)  // 뒤로 갈 수 있으면 10을 반환
		return 10;
	else  // 아무 방향으로도 가지 못하면 99 반환
		return 99;
}

int Graph::dirSouth(int r, int c)
{
	if (c + 1 < m && room[r][c + 1] == 0) // 동쪽이 0이면
		return 1;
	else if (r - 1 >= 0 && room[r - 1][c] == 0)  // 북쪽이 0이면
		return 0;
	else if (c - 1 >= 0 && room[r][c - 1] == 0) // 서쪽이 0이면
		return 3;
	else if (r + 1 < n && room[r + 1][c] == 0) // 남쪽이 0이면
		return 2;
	else if (r - 1 >= 0 && room[r - 1][c] != 1)  // 뒤로 갈 수 있으면 10을 반환
		return 10;
	else  // 아무 방향으로도 가지 못하면 99 반환
		return 99;
}

int Graph::dirNorth(int r, int c)
{
	if (c - 1 >= 0 && room[r][c - 1] == 0) // 서쪽이 0이면
		return 3;
	else if (r + 1 < n && room[r + 1][c] == 0) // 남쪽이 0이면
		return 2;
	else if (c + 1 < m && room[r][c + 1] == 0) // 동쪽이 0이면
		return 1;
	else if (r - 1 >= 0 && room[r - 1][c] == 0)  // 북쪽이 0이면
		return 0;
	else if (r + 1 < n && room[r + 1][c] != 1)  // 뒤로 갈 수 있으면 10을 반환
		return 10;
	else  // 아무 방향으로도 가지 못하면 99 반환
		return 99;
}

void Graph::clean(int r, int c, int d)
{
	int tmp;
	if (room[r][c] == 0 && res == false)  // 현재 있는 방이 청소가 되어 있지 않고 res가 false 이면
	{
		cleanCount++;  // 청소한 방의 개수 1증가
		room[r][c] = 3;  // 청소가 되었음을 표시하기 위해 임시로 3으로 변경
	}
	else if (res == true)  // 만약 res가 true 이면
		return;  // 함수 종료
	switch (d)  // 방향에 따라
	{
	case 0:  // 북쪽을 보고 있는 경우
		tmp=dirNorth(r, c);
		if (tmp == 10)  // tmp가 10이면 후진
			clean(r + 1, c, d);
		else if (tmp == 99)  // tmp가 99이면 함수 종료
		{
			res = true;
			return;
		}
		else if (tmp == 0)  // tmp가 0이면 북쪽으로 이동
			clean(r - 1, c, tmp);
		else if (tmp == 1)  // tmp가 1이면 동쪽으로 이동
			clean(r, c + 1, tmp);
		else if (tmp == 2)  // tmp가 2이면 남쪽으로 이동
			clean(r + 1, c, tmp);
		else if (tmp == 3)  // tmp가 3이면 서쪽으로 이동
			clean(r, c - 1, tmp);
		break;
	case 1:  // 동쪽을 보고 있는 경우
		tmp = dirEast(r, c);
		if (tmp == 10)
			clean(r, c - 1, d);
		else if (tmp == 99)
		{
			res = true;
			return;
		}
		else if (tmp == 0)
			clean(r - 1, c, tmp);
		else if (tmp == 1)
			clean(r, c + 1, tmp);
		else if (tmp == 2)
			clean(r + 1, c, tmp);
		else if (tmp == 3)
			clean(r, c - 1, tmp);
		break;
	case 2:  // 남쪽을 보고 있는 경우
		tmp = dirSouth(r, c);
		if (tmp == 10)
			clean(r - 1, c, d);
		else if (tmp == 99)
		{
			res = true;
			return;
		}
		else if (tmp == 0)
			clean(r - 1, c, tmp);
		else if (tmp == 1)
			clean(r, c + 1, tmp);
		else if (tmp == 2)
			clean(r + 1, c, tmp);
		else if (tmp == 3)
			clean(r, c - 1, tmp);
		break;
	case 3:  // 서쪽을 보고 있는 경우
		tmp = dirWest(r, c);
		if (tmp == 10)
			clean(r, c + 1, d);
		else if (tmp == 99)
		{
			res = true;
			return;
		}
		else if (tmp == 0)
			clean(r - 1, c, tmp);
		else if (tmp == 1)
			clean(r, c + 1, tmp);
		else if (tmp == 2)
			clean(r + 1, c, tmp);
		else if (tmp == 3)
			clean(r, c - 1, tmp);
		break;
	}
}
int main()
{
	int n, m;
	cin >> n >> m;
	int r, c, d;
	cin >> r >> c >> d;

	Graph g(n, m);
	g.inputMatrix(n, m);

	g.clean(r, c, d);
	g.printCleanCount();

	return 0;
}
