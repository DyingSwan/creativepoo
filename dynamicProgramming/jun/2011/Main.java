import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        out.write(calc(in.readLine())+"");
        out.close();
        in.close();
    }

    private static long calc(String code){
        int i, n = code.length(), MOD = 1000000;
        long dp[] = new long[n+1];
        if(code.charAt(0)=='0') return 0;       
        dp[0] = dp[1] = 1;
        for(i=2;i<=n;i++){
            if(code.charAt(i-1) > '0') dp[i] += dp[i-1];
            dp[i]%=MOD;
            if(isInRange(code.substring(i-2, i))) dp[i] += dp[i-2];
            dp[i]%=MOD;
        }
        return dp[n];
    }

    private static boolean isInRange(String num){
        int dec = Integer.parseInt(num);
        return 10<=dec && dec<=26;
    }
}
