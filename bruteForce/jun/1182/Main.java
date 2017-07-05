import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {
    public static int n, s, arr[], cnt;
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int i, visit = 0;
        String line[] = in.readLine().split(" ");
        arr = new int[n = Integer.parseInt(line[0])]; s = Integer.parseInt(line[1]);

        line = in.readLine().split(" ");
        for(i=0;i<n;i++) arr[i] = Integer.parseInt(line[i]);

        for(i=0;i<n;i++) dfs(i, arr[i], visit|(1<<i));
        out.write(cnt+"");
        out.close();
        in.close();
    }

    private static void dfs(int idx, int sum, int visit){
        if(sum==s) cnt++;
        for(int i=idx+1;i<n;i++)
            if((visit&(1<<i))==0) dfs(i, sum+arr[i], visit|(1<<i));
    }
}
