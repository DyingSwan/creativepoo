import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    public static int map[][], m,n;
    public static boolean check[][];
    public static int dx[] = {1,-1,0,0};
    public static int dy[] = {0,0,-1,1};
    public static void main(String args[])throws Exception{
        // input을 받아올 Stream과 Output을 출력한 Stream 을 생성함.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // input을 통해 미로 map을 생성함.
        String nm[] = in.readLine().split(" ");
        n=Integer.parseInt(nm[0]);
        m=Integer.parseInt(nm[1]);
        map = new int[n][m];
        check = new boolean [n][m];
        for(int i =0; i<n; i++){
            String tmp= in.readLine();
            for(int j =0; j<m; j++){
                map[i][j]=(int)(tmp.charAt(j)-'0');
            }
        }
        //bfs를  실행하여 map을 갱신 
        bfs(0,0,1);
        testPrint(map);
        out.write(Integer.toString(map[n-1][m-1]));
        out.close();
        in.close();
        
    }
    private static void bfs(int x, int y, int cnt){
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x,y));
        check[x][y]=true;
        while(!q.isEmpty()){
            Pair tmp = q.poll();
            int nx = tmp.x;
            int ny = tmp.y;
            for(int i =0; i<4; i++){
                int a = nx +dx[i];
                int b = ny +dy[i];
                if(isInIndex(a,b)&&map[a][b]==1&&check[a][b]==false){
                    q.offer(new Pair(a,b));
                    check[a][b]=true;
                    map[a][b]=map[nx][ny]+1;
                }
            }
            
        }

    }
    private static boolean isInIndex(int x, int y){
        boolean canChange = false;
        if((x>=0&&x<n) && (y>=0&&y<m))canChange=true;
        return canChange;
    }
    private static void testPrint(int map[][]){
        for(int i=0; i<n; i++){
            for(int j =0 ; j<m ; j++){
                System.out.print(map[i][j]+" ");
            }
                System.out.println();
        }
    }
}
class Pair{
    int x,y;
    Pair(int x, int y){
        this.x=x;
        this.y=y;
    }
    
}
