#include <iostream>
#include <cstdio>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;
// step1. ���ϰ��� �ϴ� �� �������� �����.
// d[n] = 'a[i]�� ���������� �ϴ�' ���� �� �����ϴ� �κ� ����.
// a[i]= ������ i ��° ����.
int d[1000];
int a[1000];
int seq(int n)
{

        // type1. Bottom-Up ���.
        // step2. �� �����.
        // ���⼭�� �� ������ ������ ����. ��Ȳ�� �ؼ��ؾ� �Ѵ�.
        for(int i = 0; i<n; i++)
        {
                d[i] = 1;
                for(int j = 0; j<i; j++)//�տ� �����鼭
                {
                        if(a[j] < a[i] && d[i] < d[j]+1)
                        {// �����鼭 ���� �� ��
                                d[i] = d[j] + 1;
                        }
                }
        }


        int max = d[0];
        for(int i = 1; i<n; i++)
        {
                if(d[i] > max) max = d[i];
        }
        return max;

}
int main(int argc, char const *argv[]) {

        int n;
        cin >> n;
        for(int i=0; i<n; i++)
        {
                cin >> a[i];
        }
        cout << seq(n) << endl;
        return 0;
}
