import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main{
    public static void main(String args[])throws Exception{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    int size = Integer.parseInt(in.readLine()); 
    out.write(calcInBottom(size, in.readLine())+"");
    out.close();
    in.close();
    }

    public static long calcInBottom(int size, String nums){
        long d[] = new long[size +1];
        String an[] = nums.split(" ");
        for(int i =0; i< size ; i++){
        int first = Integer.parseInt(an[i]);
        d[i] = first;
            if(i==0) continue;
            if(d[i]<d[i-1]+first)d[i]=d[i-1]+first;
        }      
        Arrays.sort(d);
        return d[size];
    }
}
