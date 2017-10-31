
#include <iostream>
using namespace std;


class Graph
{
	int n, m;  // ���� n , ���� m
	int **room;  // ���� 2���� �迭 room
	int cleanCount;  // û���� ���� ���� count
	bool res;  // �� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���̸鼭, ���� ������ ���̶� ������ �� �� ���� ��쿡�� �۵��� �����. true �̸� ����
public:
	Graph(int n, int m);
	~Graph();
	void inputMatrix(int n, int m);  // matrix�� �Է� �޴� �Լ�
	void clean(int r, int c, int d);  // clean ���� �Լ�
	int dirNorth(int r, int c);  // �Ĵٺ��� ������ ������ ��쿡�� �� ������ üũ�ϴ� �Լ�
	int dirSouth(int r, int c);  // �Ĵٺ��� ������ ������ ��쿡�� �� ������ üũ�ϴ� �Լ�
	int dirEast(int r, int c);  // �Ĵٺ��� ������ ������ ��쿡�� �� ������ üũ�ϴ� �Լ�
	int dirWest(int r, int c);  // �Ĵٺ��� ������ ������ ��쿡�� �� ������ üũ�ϴ� �Լ�
	void printCleanCount();  // ����� ����ϴ� �Լ�.
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
	if (r - 1 >= 0 && room[r - 1][c] == 0)  // ������ 0�̸� 
		return 0;
	else if (c - 1 >= 0 && room[r][c - 1] == 0) // ������ 0�̸�
		return 3;
	else if (r + 1 < n && room[r + 1][c] == 0) // ������ 0�̸�
		return 2;
	else if (c + 1 < m && room[r][c + 1] == 0) // ������ 0�̸�
		return 1;
	else if (c - 1 >= 0 && room[r][c - 1] != 1)  // �ڷ� �� �� ������ 10�� ��ȯ
		return 10;
	else  // �ƹ� �������ε� ���� ���ϸ� 99 ��ȯ
		return 99;
}

int Graph::dirWest(int r, int c)
{
	if (r + 1 < n && room[r + 1][c] == 0) // ������ 0�̸�
		return 2;
	else if (c + 1 < m && room[r][c + 1] == 0) // ������ 0�̸�
		return 1;
	else if (r - 1 >= 0 && room[r - 1][c] == 0)  // ������ 0�̸�
		return 0;
	else if (c - 1 >= 0 && room[r][c - 1] == 0) // ������ 0�̸�
		return 3;
	else if (c + 1 < m && room[r][c + 1] != 1)  // �ڷ� �� �� ������ 10�� ��ȯ
		return 10;
	else  // �ƹ� �������ε� ���� ���ϸ� 99 ��ȯ
		return 99;
}

int Graph::dirSouth(int r, int c)
{
	if (c + 1 < m && room[r][c + 1] == 0) // ������ 0�̸�
		return 1;
	else if (r - 1 >= 0 && room[r - 1][c] == 0)  // ������ 0�̸�
		return 0;
	else if (c - 1 >= 0 && room[r][c - 1] == 0) // ������ 0�̸�
		return 3;
	else if (r + 1 < n && room[r + 1][c] == 0) // ������ 0�̸�
		return 2;
	else if (r - 1 >= 0 && room[r - 1][c] != 1)  // �ڷ� �� �� ������ 10�� ��ȯ
		return 10;
	else  // �ƹ� �������ε� ���� ���ϸ� 99 ��ȯ
		return 99;
}

int Graph::dirNorth(int r, int c)
{
	if (c - 1 >= 0 && room[r][c - 1] == 0) // ������ 0�̸�
		return 3;
	else if (r + 1 < n && room[r + 1][c] == 0) // ������ 0�̸�
		return 2;
	else if (c + 1 < m && room[r][c + 1] == 0) // ������ 0�̸�
		return 1;
	else if (r - 1 >= 0 && room[r - 1][c] == 0)  // ������ 0�̸�
		return 0;
	else if (r + 1 < n && room[r + 1][c] != 1)  // �ڷ� �� �� ������ 10�� ��ȯ
		return 10;
	else  // �ƹ� �������ε� ���� ���ϸ� 99 ��ȯ
		return 99;
}

void Graph::clean(int r, int c, int d)
{
	int tmp;
	if (room[r][c] == 0 && res == false)  // ���� �ִ� ���� û�Ұ� �Ǿ� ���� �ʰ� res�� false �̸�
	{
		cleanCount++;  // û���� ���� ���� 1����
		room[r][c] = 3;  // û�Ұ� �Ǿ����� ǥ���ϱ� ���� �ӽ÷� 3���� ����
	}
	else if (res == true)  // ���� res�� true �̸�
		return;  // �Լ� ����
	switch (d)  // ���⿡ ����
	{
	case 0:  // ������ ���� �ִ� ���
		tmp=dirNorth(r, c);
		if (tmp == 10)  // tmp�� 10�̸� ����
			clean(r + 1, c, d);
		else if (tmp == 99)  // tmp�� 99�̸� �Լ� ����
		{
			res = true;
			return;
		}
		else if (tmp == 0)  // tmp�� 0�̸� �������� �̵�
			clean(r - 1, c, tmp);
		else if (tmp == 1)  // tmp�� 1�̸� �������� �̵�
			clean(r, c + 1, tmp);
		else if (tmp == 2)  // tmp�� 2�̸� �������� �̵�
			clean(r + 1, c, tmp);
		else if (tmp == 3)  // tmp�� 3�̸� �������� �̵�
			clean(r, c - 1, tmp);
		break;
	case 1:  // ������ ���� �ִ� ���
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
	case 2:  // ������ ���� �ִ� ���
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
	case 3:  // ������ ���� �ִ� ���
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
