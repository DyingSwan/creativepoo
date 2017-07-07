import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
	public static int map[][], n;
	public static boolean visit[];
	public static StringBuilder res = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line[] = in.readLine().split(" "); 
		n = Integer.parseInt(line[0]);
		map = new int[n][n]; 
		visit = new boolean[n];
		
		int a, b, i, m = Integer.parseInt(line[1]), v = Integer.parseInt(line[2]);
		for(i=0;i<m;i++){
			line = in.readLine().split(" ");
			a = Integer.parseInt(line[0])-1; b = Integer.parseInt(line[1])-1;
			map[a][b] = map[b][a] = 1;
		}
		
		dfs(v-1);
		res.append("\n");
		
		visit = new boolean[n];
		bfs(v-1);
		
		out.write(res.toString());
		out.close();
		in.close();
	}
	
	private static void dfs(int x){
		visit[x] = true; 
		res.append((x+1)+" ");
		for(int i=0;i<n;i++) 
			if(visit[i]==false && map[x][i]==1) 
				dfs(i);
	}
	
	private static void bfs(int x){
		Queue<Integer> q = new LinkedList<>();
		q.offer(x); 
		visit[x] = true; 
		
		int i, here;
		while(!q.isEmpty()){
			here = q.poll();
			res.append((here+1)+" ");
			for(i=0;i<n;i++)
				if(visit[i]==false && map[here][i]==1){
					q.offer(i);
					visit[i] = true;
				}
		}
	}
}