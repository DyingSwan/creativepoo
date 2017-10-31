import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main{
	public static int N, A[], ops[], min = 0x7fffffff, max = -min;
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int i, j, k, l;
		N = Integer.parseInt(in.readLine());
		A = new int[N]; ops = new int[N-1];
		String line[] = in.readLine().split(" ");
		for(i=0;i<N;i++) A[i] = Integer.parseInt(line[i]);
		line = in.readLine().split(" ");
		for(i=k=0;i<4;i++) {
			l = Integer.parseInt(line[i]);
			for(j=0;j<l;j++) ops[k++] = i;
		}
		dfs("");
		out.write(max+"\n"+min);

		out.close();
		in.close();
	}

	private static void dfs(String op){
		if(op.length()==N-1){
			calc(op);
			return;
		}
		for(int i=0,k;i<N-1;i++){
			if(ops[i]==-1) continue;
			k = ops[i]; ops[i] = -1;
			if(k==0) dfs(op+'+');
			else if(k==1) dfs(op+'-');
			else if(k==2) dfs(op+'*');
			else dfs(op+'/');
			ops[i] = k;
		}
	}

	private static void calc(String op){
		char ch;
		int i, a = A[0], b;
		for(i=0;i<N-1;i++){
			ch = op.charAt(i); b = A[i+1];
			if(ch=='+') a+=b;
			else if(ch=='-') a-=b;
			else if(ch=='*') a*=b;
			else a/=b;
		}
		max = Math.max(max, a);
		min = Math.min(min, a);
	}
}
