import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main{
    static final int U = 0, D = 1, L = 2, R = 3, DIV = 10;
    static final int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1}; //순서대로 UDLR
    static final int[][] UDLR = { {-1,0}, {1,0}, {0,-1}, {0,1} }; // 순서대로 UP, DOWN, LEFT, RIGHT
    static final int DIR[][][] = {
        { UDLR[U], null, null, UDLR[R] },           // [0] 2번 모양
        { UDLR[U], null, UDLR[L], null },           // [1] 3번 모양
        { null, UDLR[D], UDLR[L], null },           // [2] 4번 모양
        { UDLR[U], UDLR[D], UDLR[L], UDLR[R] },     // [3] + 모양
        { UDLR[U], UDLR[D] , null, null },          // [4] | 모양
        { null, null, UDLR[L], UDLR[R] },           // [5] - 모양
        {},{},{},                                   // [6, 7, 8]은 없음
        { null, UDLR[D], null, UDLR[R] }            // [9] 1번 모양
    };
    static HashSet<Integer> suspect = new HashSet<>(); //도둑맞은 좌표 정보 저장
    static int r, c, susX, susY; //도둑맞은 x, y 좌표
    static boolean visit[][];
    static char map[][];

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 변수선언 및 입력정보 처리 */
        int i, j, sX = -1, sY = -1, ax, ay;
        String line = in.readLine();
        r = Integer.parseInt(line.substring(0, line.indexOf(" ")));
        c = Integer.parseInt(line.substring(line.indexOf(" ")+1));
        visit = new boolean[r][c];
        map = new char[r][c];

        for(i=0;i<r;i++) {
            line = in.readLine();
            for(j=0;j<c;j++) {
                map[i][j] = line.charAt(j);
                /* 시작점 찾음 */
                if(map[i][j]=='M') { sX = i; sY = j; }
            }
        }

        /* 시작점에서 DFS 탐색 */
        for(i=0;i<4;i++) {
            ax = sX+dx[i]; ay = sY+dy[i];
            if(canGo(ax, ay)) dfs(ax, ay);
        }

        /* 도둑맞은 자리에서 4방향 탐색 */
        calcFromSuspect();

        /* 도둑맞은 좌표 및 적절한 파이프 출력 */
        out.write(judge());
        out.close();
        in.close();
    }

    /* M 과 이어진 길을 따라가면서 끊길 때 까지 탐색
     * 파이프 길인데 끊겼다(.) == 도둑맞은 장소 발견 */
    private static void dfs(int x, int y) {
        int i, ax, ay, divNum = map[x][y] % DIV;
        visit[x][y] = true;

        for(i=0;i<4;i++) {
            if(DIR[divNum][i] == null) continue;

            ax = x + DIR[divNum][i][0];
            ay = y + DIR[divNum][i][1];

            if(map[ax][ay]=='M' || map[ax][ay]=='Z') continue;
            else if(map[ax][ay] == '.') { susX = ax; susY = ay; }
            else if(!visit[ax][ay]) dfs(ax, ay);
        }
        visit[x][y] = false;
    }

    /* 지도 밖으로 안나가게 걸러줌 */
    private static boolean isInRange(int x, int y) {
        return (0<=x&&x<r) && (0<=y&&y<c);
    }

    /* 시작점에서 DFS 시작할 때 갈 수 있는 파이프 탐색 */
    private static boolean canGo(int x, int y) {
        return isInRange(x, y) && !(map[x][y]=='.'||map[x][y]=='M'||map[x][y]=='Z');
    }

    /* 도둑맞은 공간에서 4방으로 탐색 */
    private static void calcFromSuspect() {
        int i, ax, ay, next;
        for(i=0;i<4;i++) {
            ax = susX + dx[i]; ay = susY + dy[i];

            if(isInRange(ax, ay)) {
                next = map[ax][ay] % DIV;
                if(i==U && (next==3||next==9||next==2||next==4)) suspect.add(U);
                else if(i==D && (next==3||next==0||next==1||next==4)) suspect.add(D);
                else if(i==L && (next==3||next==5||next==9||next==0)) suspect.add(L);
                else if(i==R && (next==3||next==5||next==1||next==2)) suspect.add(R);
            }
        }
    }

    //도둑맞은 공간의 정보(HashSet)를 바탕으로 적절한 파이프 선택 
    private static String judge() {
        String res = String.format("%d %d ", susX+1, susY+1);
        if(suspect.size()==4) res += "+";
        else {
            if(suspect.contains(L)&&suspect.contains(R)) res+="-";
            else if(suspect.contains(D)&&suspect.contains(R)) res+="1";
            else if(suspect.contains(U)&&suspect.contains(R)) res+="2";
            else if(suspect.contains(U)&&suspect.contains(L)) res+="3";
            else if(suspect.contains(D)&&suspect.contains(L)) res+="4";
            else if(suspect.contains(U)&&suspect.contains(D)) res+="|";
        }
        return res;
    }
}
