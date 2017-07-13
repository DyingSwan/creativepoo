/* 풀이: http://blog.naver.com/occidere/221050474239 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.awt.Point;
import java.util.Stack;
import java.util.PriorityQueue;

public class Main{
    public static int n, map[][];
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int i, j; n = Integer.parseInt(in.readLine());
        map = new int[n][n];
        String line;
        for(i=0;i<n;i++){
            line = in.readLine();
            for(j=0;j<n;j++) map[i][j] = line.charAt(j) - 48;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                if(map[i][j]==1) {
                    pq.offer(dfs(i, j));
                }

        StringBuilder res = new StringBuilder(pq.size()+"\n");
        while(!pq.isEmpty()) res.append(pq.poll()+"\n");

        out.write(res.toString());
        out.close();
        in.close();
    }

    private static int dfs(int a, int b){
        int i, cnt = 1, x, y, ax, ay, dx[] = {-1,1,0,0}, dy[] = {0,0,-1,1};
        Stack<Point> stk = new Stack<>();
        stk.push(new Point(a, b));
        map[a][b] = 0;

        while(!stk.isEmpty()){
            x = stk.peek().x; y = stk.pop().y;

            for(i=0;i<4;i++){
                ax = x+dx[i]; ay = y+dy[i];
                if(isInRange(ax, ay) && map[ax][ay] == 1){
                    map[ax][ay] = 0;
                    cnt++;
                    stk.push(new Point(ax, ay));
                }
            }
        }
        return cnt;
    }

    private static boolean isInRange(int x, int y){
        return (0<=x&&x<n) && (0<=y&&y<n);
    }
}
