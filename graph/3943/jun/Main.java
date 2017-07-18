import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(in.readLine());
        long n;
        StringBuilder res = new StringBuilder();

        while(t-->0) {
            n = Long.parseLong(in.readLine());
            res.append(hailStone(n)+"\n");
        }
        out.write(res.toString());
        out.close();
        in.close();
    }

    private static long hailStone(long n) {
        long max = -1;
        while(true) {
            if(max < n) max = n;
            if(n==1||n==2||n==4) break;
            else if(n%2 == 0) {
                if(is2N(n)) break;
                else n>>>=1;
            }
            else n = n*3+1;
        }
        return max;
    }

    private static boolean is2N(long n) {
        if(n%2==1) return false;
        else if(n==0) return true;
        else return is2N(n>>>1);
    }
}
