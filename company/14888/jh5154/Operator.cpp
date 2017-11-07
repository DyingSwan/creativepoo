#include <stdio.h>
#include <algorithm>
using namespace std;

int n;
int A[11]; // 숫자 받는다
int Plus, Minus, Mul, Div;
int Max = -1000000000;
int Min = 1000000000;

void solve(int idx, int sum, int p, int mi, int mu, int d){
	if (idx == n) {
		Max = max(Max, sum);
		Min = min(Min, sum);
		return;
	}
	if (p) solve(idx+1, sum + A[idx], p - 1, mi, mu, d);
	if (mi) solve(idx+1, sum - A[idx], p, mi - 1, mu, d);
	if (mu) solve(idx+1, sum*A[idx], p, mi, mu - 1, d);
	if (d) solve(idx+1, sum / A[idx], p, mi, mu, d - 1);
}

int main() {

	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		scanf("%d", A + i);
	}

	scanf("%d", &Plus);
	scanf("%d", &Minus);
	scanf("%d", &Mul);
	scanf("%d", &Div);

	solve(1, A[0], Plus, Minus, Mul, Div);

	printf("%d\n", Max);
	printf("%d\n", Min);
	return 0;
}