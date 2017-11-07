#include <stdio.h>
#include <algorithm>
using namespace std;

int max_val = -1000000000;
int min_val = 1000000000;

void find(int index, int value, int number, int num_arr[], int plus, int minus, int multi, int div)
{
	if (index == number)
	{
		max_val = max(max_val, value);
		min_val = min(min_val, value);
		return;
	}

	if (plus)
		find(index + 1, value + num_arr[index], number, num_arr, plus - 1, minus, multi, div);
	if (minus)
		find(index + 1, value - num_arr[index], number, num_arr, plus, minus - 1, multi, div);
	if (multi)
		find(index + 1, value*num_arr[index], number, num_arr, plus, minus, multi - 1, div);
	if (div)
		find(index + 1, value / num_arr[index], number, num_arr, plus, minus, multi, div - 1);

}

int main()
{
	int number, num_arr[11];
	scanf_s("%d", &number);

	for (int i = 0; i < number; i++)
		scanf_s("%d", &num_arr[i]);

	int plus, minus, mul, div;

	scanf_s("%d", &plus);
	scanf_s("%d", &minus);
	scanf_s("%d", &mul);
	scanf_s("%d", &div);

	find(1, num_arr[0], number, num_arr, plus, minus, mul, div);

	printf("%d\n", max_val);
	printf("%d\n", min_val);

	return 0;
}