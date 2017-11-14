import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static boolean visit[] = new boolean[4];
	public static int w[][] = new int[4][8];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int i, j, K, pos, ccw;
		String line[] = new String[1];
		for(i=0;i<4;i++){
			line[0] = in.readLine();
			for(j=0;j<8;j++) w[i][j] = line[0].charAt(j)-48;
		}
		K = Integer.parseInt(in.readLine());
		for(i=0;i<K;i++){
			line = in.readLine().split(" ");
			pos = Integer.parseInt(line[0])-1;
			ccw = Integer.parseInt(line[1]);
			visit[pos] = true;
			spin(pos, ccw);
			rotate(w[pos], ccw);
			visit[pos] = false;
		}
		out.write(getScore()+"");

		out.close();
		in.close();
	}

	private static void spin(int pos, int ccw){
		if(0<pos && !visit[pos-1] && w[pos-1][2]!=w[pos][6]){
			visit[pos-1] = true;
			spin(pos-1, -ccw);
			visit[pos-1] = false;
			rotate(w[pos-1], -ccw);
		}
		if(pos<3 && !visit[pos+1] && w[pos+1][6]!=w[pos][2]) {
			visit[pos+1] = true;
			spin(pos+1, -ccw);
			visit[pos+1] = false;
			rotate(w[pos+1], -ccw);
		}
	}

	private static void rotate(int w[], int ccw){
		int i, s = (ccw==-1) ? 0 : 7, e = (ccw==-1) ? 7 : 0, tmp;
		for(i=s;i!=e;i-=ccw){
			tmp = w[i];
			w[i] = w[(8+i-ccw)%8];
			w[(8+i-ccw)%8] = tmp;
		}
	}

	private static int getScore(){
		int i, score = 0;
		for(i=0;i<4;i++) score += w[i][0]<<i;
		return score;
	}
}
