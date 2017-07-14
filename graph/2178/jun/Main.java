import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    public static int n, m, map[][];
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String line = in.readLine();
        n = Integer.parseInt(line.substring(0, line.indexOf(" ")));
        m = Integer.parseInt(line.substring(line.indexOf(" ")+1));
        
        int i, j; map = new int[n][m];
        for(i=0;i<n;i++){
            line = in.readLine();
            for(j=0;j<m;j++) map[i][j] = line.charAt(j)-48;
        }
        
        out.write(bfs(0, 0)+"");
        out.close();
        in.close();
    }

    private static int bfs(int a, int b){
        int i, x, y, ax, ay, dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};

        Queue<Elements> q = new LinkedList<>();
        q.offer(new Elements(a, b));

        while(!q.isEmpty()){
            x = q.peek().x; y = q.poll().y;

            for(i=0;i<4;i++){
                ax = x + dx[i]; ay = y + dy[i];

                if(isInRange(ax, ay) && (map[ax][ay]==1 || map[x][y]+1<map[ax][ay])){
                    map[ax][ay] = map[x][y]+1;
                    q.offer(new Elements(ax, ay));
                }
            }
        }
        return map[n-1][m-1];
    }

    private static boolean isInRange(int x, int y){
        return (0<=x&&x<n)&&(0<=y&&y<m);
    }
}

class Elements{
    int x, y;
    public Elements(int x, int y){
        this.x = x;
        this.y = y;
    }
}
