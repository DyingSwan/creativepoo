import java.util.*;
import java.io.*;

public class Main{
	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

	static int nums;
	static int max;
	static int min;
	static int[] numArr;
//	static int check= 0;

	public static void main(String[] args) throws Exception{
	//인풋 받기.
	String[] s = in.readLine().split(" ");
	//숫자 갯수 받아오기
	nums = Integer.parseInt(s[0]);

	//인풋 다음 줄.
	s = in.readLine().split(" ");

	//실제 숫자를 배열에 할당.
	numArr = new int[nums];
	for(int i = 0; i < nums; i++)
		numArr[i] = Integer.parseInt(s[i]);

	//인풋 다음 줄
	s = in.readLine().split(" ");

	//연산자 배열에 값 할당.
	int[] k = new int[4];
	for(int i = 0; i < 4; i++)
		k[i] = Integer.parseInt(s[i]);

	max = -987654321;
	min = -max;

	dfs("",k);

	System.out.println(max + "\n" + min);
	
	}

	public static void dfs(String s,int[] k){
		int[] tmp = new int[4];
		for(int i = 0; i<4; i++)
			tmp[i] = k[i];
		int a = 0;
			if(tmp[0] != 0){
				a = tmp[0];
				tmp[0] -= 1;
				dfs(s+'+',tmp);
				tmp[0] = a;
			}
			if(tmp[1] != 0){
				a = tmp[1];
				tmp[1] -= 1;
				dfs(s+'-',tmp);
				tmp[1] = a;
			}
			if(tmp[2] != 0){
				a = tmp[2];
				tmp[2] -= 1;
				dfs(s+'*',tmp);
				tmp[2] = a;
			}
			if(tmp[3] != 0){
				a = tmp[3];
				tmp[3] -= 1;
				dfs(s+'/',tmp);
				tmp[3] = a;
			}


		if(s.length() == nums -1)
			cal(s);
		}
	
	public static void cal(String s){
//		check += 1;
//		System.out.println(s + "        " + check);
		int n = numArr[0];
		int b = 0;
		for(int i = 0; i < nums-1; i++){
			b = numArr[i+1];
			char k = s.charAt(i);
			if(k == '+')
				n += b;
			if(k == '-')
				n -= b;
			if(k == '*')
				n *= b;
			if(k == '/'){
				if(n < 0)
					n = ((n * -1) / b) * -1;
				else
					n /= b;

			}
	
			
		}
			max = Math.max(n,max);
			min = Math.min(n,min);

	}
}
