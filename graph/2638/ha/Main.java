import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Main {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int map[][];
    public static int n,m;
    public static boolean visit[][];
    public static int dx[] = {0,1,0,-1};
    public static int dy[] = {1,0,-1,0};
    public static void main(String args[])throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String nm = in.readLine();
        n = Integer.parseInt((nm.split(" "))[0]);
        m = Integer.parseInt((nm.split(" "))[1]);
        map=new int[n][m];
        visit = new boolean[n][m];
        
        String tmp;
        String a[];
        for(int i=0; i<n; i++){
            tmp= in.readLine();
            a=tmp.split(" ");
            for(int j =0; j<m; j++){
                map[i][j]=Integer.parseInt(a[j]);
            }
        }
        int count= 0;
        while(!isClean()){
            dfs(0,0);
            count++;
            refresh();
        }
        out.write(count+"\n");
        
        in.close();
        out.close();
    }
    public static void testPrint(){
        System.out.println();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean dfs(int x, int y){
        boolean complete =false;
        visit[x][y]=true;
        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            
            if(isInRange(nx,ny)&&map[x][y]!=1){
                if(visit[nx][ny]==false)dfs(nx,ny);
                if(isInRange(nx,ny)&&map[nx][ny]>=1)map[nx][ny]++;
            }
        }
        complete=true;
                
        return complete;
    }
    public static boolean isInRange(int x, int y){
        boolean correct=false;
        if(x>=0&&x<n&&y>=0&&y<m)correct =true;
        return correct;
    }
    public static void refresh(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                visit[i][j]=false;
                if(map[i][j]>=3)map[i][j]=0;
                else if(map[i][j]!=0)map[i][j]=1;
            }
        }
    }
    public static boolean isClean(){
        boolean clean =false;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j]!=0)return clean;
            }
        }
        clean =true;
        return clean;
    }
}

