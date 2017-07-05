import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    public static int n, arr[];
    public static ArrayList<Integer> lis = new ArrayList<>();
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        arr = new int[n=Integer.parseInt(in.readLine())];
        String line[]=in.readLine().split(" ");
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(line[i]);
        out.write(getLis()+"");
        out.close();
        in.close();
    }

    private static int getLis(){
        int i, size, k;
        for(i=0;i<n;i++){
            size = lis.size(); k = arr[i];
            if(size==0 || lis.get(size-1)<k) lis.add(k);
            else lis.set(lowerBound(0, size, k), k);
        }
        return lis.size();
    }

    private static int lowerBound(int front, int rear, int k){
        int mid;
        while(front<rear){
            mid = (front+rear)>>1;
            if(lis.get(mid)<k) front=mid+1;
            else rear=mid;
        }
        return rear;
    }
}
