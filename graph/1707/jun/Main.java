import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	static ArrayList<Integer> map[];
	public static int v, e, color[];
	public static boolean isOk;
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int i, j, a, b, t = Integer.parseInt(in.readLine());
		String line[];
		StringBuilder res = new StringBuilder();
		while(t-->0){
			isOk = true;
			line = in.readLine().split(" ");
			v = Integer.parseInt(line[0]);
			e = Integer.parseInt(line[1]);
			color = new int[v];
			map = new ArrayList[v];
			for(i=0;i<v;i++) map[i] = new ArrayList<>();
			for(i=0;i<e;i++){
				line = in.readLine().split(" ");
				a = Integer.parseInt(line[0])-1;
				b = Integer.parseInt(line[1])-1;
				map[a].add(b);
				map[b].add(a);
			}
			for(j=0;j<v;j++)
			    if(color[j]==0) dfs(j, 1);
			for(j=0;j<v;j++){
				for(int node : map[j]){
					if (color[j]==color[node]){
						isOk = false;
						break;
					}
				}
			}
			res.append(isOk?"YES\n":"NO\n");
		}
		out.write(res.toString());
		out.close();
		in.close();
	}
	
	private static void dfs(int cur, int clr){
		color[cur] = clr;
		for(int next : map[cur]){
			if(color[next] == 0) dfs(next, -clr);
			else if(color[next]==color[cur]){
				isOk = false;
				return;
			}
		}
	}
}
