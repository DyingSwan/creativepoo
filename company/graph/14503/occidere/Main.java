import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
	public static int n, m, map[][], cnt = 1;
	public static int dir[][] = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		String line[] = in.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		int i, j, r, c, d;
		line = in.readLine().split(" ");
		r = Integer.parseInt(line[0]);
		c = Integer.parseInt(line[1]);
		d = Integer.parseInt(line[2]);
		map = new int[n][m];
		for(i=0;i<n;i++) {
			line = in.readLine().split(" ");
			for(j=0;j<m;j++) map[i][j] = Integer.parseInt(line[j]);
		}

		clean(r, c, d);

		out.write((cnt-1)+"");
		out.close();
		in.close();
	}

	private static void clean(int x, int y, int curDir) {
		map[x][y] = ++cnt;

		int ax, ay, blocked = 0, back[];
		while(true) {
			curDir = turnLeft(curDir);
			ax = x+dir[curDir][0]; ay = y+dir[curDir][1];
			if(isInRange(ax, ay) && map[ax][ay]==0) {
				map[x = ax][y = ay] = ++cnt;
				blocked = 0;
			}
			else {
				blocked++;

				if(blocked == 4) {
					blocked = 0;
					back = goBack(x, y, curDir);

					if(map[x+back[0]][y+back[1]] == 1) return;
					else { x+=back[0];	y+=back[1]; }
				}
			}
		}
	}

	private static int turnLeft(int curDir) {
		return curDir-1<0 ? 3 : curDir-1;
	}

	private static boolean isInRange(int x, int y) {
		return (0<=x&&x<n) && (0<=y&&y<m);
	}

	private static int[] goBack(int x, int y, int curDir) {
		switch(curDir) {
			case 0: return dir[2];
			case 1: return dir[3];
			case 2: return dir[0];
			case 3: return dir[1];
		}
		return null;
	}
}

class Elements{
	int x, y;
	public Elements(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
