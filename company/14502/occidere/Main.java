import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
	public static int N, M, map[][];
	public static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int i, j;
		String line[] = in.readLine().split(" ");
		N = Integer.parseInt(line[0]); M = Integer.parseInt(line[1]);
		map = new int[N][M];
		for(i=0;i<N;i++){
			line = in.readLine().split(" ");
			for(j=0;j<M;j++) map[i][j] = Integer.parseInt(line[j]);
		}
		out.write(buildWall(map, 0)+"");

		out.close();
		in.close();
	}

	private static int buildWall(int map[][], int step){
		if(step==3) return getSafe(map);

		int i, j, safe = -1;
		for(i=0;i<N;i++)
			for(j=0;j<M;j++)
				if(map[i][j]==0){
					map[i][j] = 1;
					safe = Math.max(buildWall(map, step+1), safe);
					map[i][j] = 0;
				}
		return safe; //return max safe area
	}

	private static int getSafe(int map[][]){
		int i, j, area = 0, backup[][] = copy(map);

		/* spread virus */
		for(i=0;i<N;i++)
			for(j=0;j<M;j++)
				if(backup[i][j]==2)
					spreadVirus(backup, i, j);

		/* calc empty room */
		for(i=0;i<N;i++)
			for(j=0;j<M;j++)
				if(backup[i][j]==0) area++;

		return area;
	}

	private static void spreadVirus(int map[][], int x, int y){
		int i, ax, ay;
		for(i=0;i<4;i++){
			ax = x+dx[i]; ay = y+dy[i];
			if(isInRange(ax, ay) && map[ax][ay]==0){
				map[ax][ay] = 2;
				spreadVirus(map, ax, ay);
			}
		}
	}

	private static boolean isInRange(int x, int y){
		return (0<=x&&x<N) && (0<=y&&y<M);
	}

	private static int[][] copy(int map[][]){
		int i, j, newArr[][] = new int[N][M];
		for(i=0;i<N;i++)
			for(j=0;j<M;j++)
				newArr[i][j] = map[i][j];
		return newArr;
	}

	public static void print(int map[][]){
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
