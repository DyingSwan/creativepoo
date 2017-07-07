import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main{
	public static int dx[] = {0,0,-1,1}, dy[] = {1,-1,0,0}, n, m, map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line[] = in.readLine().split(" ");
		n = Integer.parseInt(line[0]); 
		m = Integer.parseInt(line[1]);
		map = new int[n][m];
		int i, j;
		for(i=0;i<n;i++){
			line = in.readLine().split(" ");
			for(j=0;j<m;j++) map[i][j] = Integer.parseInt(line[j]);
		}
		
		for(i=0;!allDissolved();i++) dfs(0, 0);
		
		out.write(String.valueOf(i));
		out.close();
		in.close();
	}
	
	private static boolean allDissolved(){
		int i, j, cnt = 0;
		for(i=0;i<n;i++){
			for(j=0;j<m;j++){
				if(map[i][j]==-1 || map[i][j]>2) map[i][j] = 0;
				else if(map[i][j]==2 || map[i][j] == 1){
					map[i][j] = 1;
					cnt++;
				}
			}
		}
		return cnt == 0;
	}
	
	//외부는 언제나 (0,0)과 연결 -> -1로 마킹
	private static void dfs(int x, int y){
		map[x][y] = -1;
		int i, ax, ay;
		for(i=0;i<4;i++){
			ax = x+dx[i]; ay = y+dy[i];
			if(isInRange(ax, ay)){
				if(map[ax][ay]==0) dfs(ax, ay);
				else if(map[ax][ay]>0) map[ax][ay]++;
			}
		}
	}
	
	private static boolean isInRange(int x, int y){
		return (0<=x&&x<n) && (0<=y&&y<m);
	}
}