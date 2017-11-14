#include <iostream>
#include <string>
using namespace std;

int copy_wheel[8];

void rotation(int wheel[],int dir)
{
	for (int i = 0; i < 8; i++)
		copy_wheel[i] = wheel[i];

	if (dir == 1)
	{
		for (int i = 0; i < 8; i++)
		{
			if (i == 0)
				wheel[0] = copy_wheel[7];
			else
				wheel[i] = copy_wheel[i - 1];
		}
	}
	else if (dir == -1)
	{
		for (int i = 0; i < 8; i++)
		{
			if (i == 7)
				wheel[7] = copy_wheel[0];
			else
				wheel[i] = copy_wheel[i + 1];
		}
	}
}

int main()
{
	int first[8], second[8], third[8], fourth[8];
	int first_dir, second_dir, third_dir, fourth_dir;

	string str;

	for (int i = 0; i < 4; i++)
	{
		cin >> str;
		if (i == 0)
			for (int j = 0; j < 8; j++)
				first[j] = int(str[j] - 48);
		else if (i == 1)
			for (int j = 0; j < 8; j++)
				second[j] = int(str[j] - 48);
		else if (i == 2)
			for (int j = 0; j < 8; j++)
				third[j] = int(str[j] - 48);
		else if (i == 3)
			for (int j = 0; j < 8; j++)
				fourth[j] = int(str[j] - 48);
	}

	int k, number, dir;
	cin >> k;
	for (int i = 0; i < k; i++)
	{
		cin >> number >> dir;
		switch (number)
		{
		case 1:
			first_dir = dir;
			if (first[2] == second[6])
				second_dir = 0;
			else
				second_dir = first_dir * -1;
			if (second[2] == third[6])
				third_dir = 0;
			else
				third_dir = second_dir * -1;
			if (third[2] == fourth[6])
				fourth_dir = 0;
			else
				fourth_dir = third_dir * -1;
			rotation(first, first_dir);
			rotation(second, second_dir);
			rotation(third, third_dir);
			rotation(fourth, fourth_dir);
			break;
		case 2:
			second_dir = dir;
			if (second[6] == first[2])
				first_dir = 0;
			else
				first_dir = second_dir * -1;
			if (third[6] == second[2])
				third_dir = 0;
			else
				third_dir = second_dir*-1;
			if (fourth[6] == third[2])
				fourth_dir = 0;
			else
				fourth_dir = third_dir*-1;
			rotation(first, first_dir);
			rotation(second, second_dir);
			rotation(third, third_dir);
			rotation(fourth, fourth_dir);
			break;
		case 3:
			third_dir = dir;
			if (third[6] == second[2])
				second_dir = 0;
			else
				second_dir = third_dir*-1;
			if (second[6] == first[2])
				first_dir = 0;
			else
				first_dir = second_dir *-1;
			if (fourth[6] == third[2])
				fourth_dir = 0;
			else
				fourth_dir = third_dir*-1;
			rotation(first, first_dir);
			rotation(second, second_dir);
			rotation(third, third_dir);
			rotation(fourth, fourth_dir);
			break;
		case 4:
			fourth_dir = dir;
			if (fourth[6] == third[2])
				third_dir = 0;
			else
				third_dir = fourth_dir*-1;
			if (third[6] == second[2])
				second_dir = 0;
			else
				second_dir = third_dir*-1;
			if (second[6] == first[2])
				first_dir = 0;
			else
				first_dir = second_dir*-1;
			rotation(first, first_dir);
			rotation(second, second_dir);
			rotation(third, third_dir);
			rotation(fourth, fourth_dir);
			break;
		default:
			break;
		}
	}

	cout << first[0] * 1 + second[0] * 2 + third[0] * 4 + fourth[0] * 8 << endl;
	return 0;
}
