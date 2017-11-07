import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int n , m;
    public static int map[][];
    public static int dx[] = {0,1,-1,0};
    public static int dy[] = {1,0,0,-1};

    public static void main(String args[]) throws Exception {
        getInput();
        int score = buildWall(0);

        out.write(score+ "");
        in.close();
        out.close();
    }

    public static int getInput() throws Exception{
        int cnt = 0;
        String[]line = in.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        map = new int [n][m];
        for(int i =0; i<n; i++) {
            line = in.readLine().split(" ");
            for(int j =0; j<m; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if(map[i][j]==0)cnt ++;
            }
        }
        return cnt;
    }

    public static void test()throws Exception {
        for(int i =0; i<n; i++) {
            for(int j =0; j<m; j++) {
                out.write(map[i][j]+" ");
            }out.write("\n");
        }
    }


    public static void flood(int m[][],int x, int y) {
        int tx = x;
        int ty = y;
        for(int i =0; i< 4; i++) {
            tx = x+dx[i];
            ty =y+dy[i];
            if (isInRange(tx,ty)&&m[tx][ty]==0) {
                m[tx][ty]=3;
                flood(m,tx,ty);
            }
        }
        return ;
    }
    public static int  calc(int map[][]) {
        int cnt = 0;
        int tmp[][] = new int[n][m];
        for(int i =0; i<n; i++) {
            tmp[i] = Arrays.copyOf(map[i], map[i].length);
        }
        for(int i =0; i<n; i++)
            for(int j =0; j<m; j++) {
                if(map[i][j]==2) {
                    flood(tmp,i,j);
                }
            }
        for(int i=0;i<n; i++) {
            for(int j =0; j<m; j++) {
                if (tmp[i][j]== 0) cnt ++;
            }
        }
        return cnt;
    }
    public static int buildWall(int step){
        if(step==3) return calc(map);
        int i, j, score = -1;

        for(i=0;i<n;i++)
            for(j=0;j<m;j++)
                if(map[i][j]==0){
                    map[i][j] = 1;
                    score = Math.max(buildWall(step+1), score);
                    map[i][j] = 0;
                }
        return score; 
    }
    public static boolean isInRange(int x , int y) {
        return x>=0 && y >= 0 && x<n && y<m;
    }
}
