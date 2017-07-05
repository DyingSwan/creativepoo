import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        long x, y, z, z2;
        double k;
        String line;
        StringBuilder res = new StringBuilder();
        while((line = in.readLine()) != null){
            x = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            y = Integer.parseInt(line.substring(line.indexOf(' ')+1));
            if((z = y * 100 / x)>=99) res.append("-1\n");
            else{
                z2 = z + 1;
                k = (double)(z2*x - 100*y) / (double)(100 - z2);
                res.append((long)Math.ceil(k)+"\n");
            }
        }
        out.write(res.toString());
        out.close();
        in.close();
    }
}
