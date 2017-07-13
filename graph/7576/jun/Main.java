/* 풀이: http://blog.naver.com/occidere/221050425303 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int RIPEN = 1, UNRIPEN = 0, EMPTY = -1;
    public static int n, m, map[][];
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int i, j;
        String line[] = in.readLine().split(" ");
        n = Integer.parseInt(line[1]);
        m = Integer.parseInt(line[0]);
        map = new int[n][m];
        LinkedList<Elements> pos = new LinkedList<>();
        for(i=0;i<n;i++){
            line = in.readLine().split(" ");
            for(j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if(map[i][j]==RIPEN) pos.add(new Elements(i, j));
            }
        }
        for(Elements e : pos) {
            bfs(e.x, e.y);
            //print();
        }
        out.write(getMaxDate()+"");
        out.close();
        in.close();
    }

    private static void bfs(int a, int b){
        Queue<Elements> q = new LinkedList<>();
        q.offer(new Elements(a, b));

        int i, x, y, ax, ay, dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1};
        while(!q.isEmpty()){
            x = q.peek().x; y = q.poll().y;
            for(i=0;i<4;i++){
                ax = x+dx[i]; ay = y+dy[i];
                if(canSpread(x, y, ax, ay)){
                    map[ax][ay] = map[x][y]+1;
                    q.offer(new Elements(ax, ay));
                }
            }
        }
    }

    private static boolean canSpread(int x, int y, int ax, int ay){
        boolean isInRange = (0<=ax&&ax<n)&&(0<=ay&&ay<m);
        return isInRange && (map[ax][ay] == UNRIPEN || map[x][y]+1 < map[ax][ay]);
    }

    private static int getMaxDate(){
        int i, j, max = -1;
        for(i=0;i<n;i++)
            for(j=0;j<m;j++){
                if(map[i][j]==UNRIPEN) return -1;
                else if(max < map[i][j]) max = map[i][j];
            }
        return max-1;
    }

    private static void print(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

class Elements{
    int x, y;
    public Elements(int x, int y){
        this.x = x;
        this.y = y;
    }
}
