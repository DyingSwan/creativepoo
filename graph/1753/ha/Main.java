package shortest_1753;

import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
class Dist implements Comparable<Dist>{
    int v;
    int w;
    public Dist (int v , int w){
        this.v=v; this.w=w;
    }
    public int compareTo(Dist next){
        return w<=next.w ?-1 :1 ;
    }
}

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static PriorityQueue<Integer> dist = new PriorityQueue<>();
    public static ArrayList<Dist> data[];
    public static boolean visit[];
    @SuppressWarnings("unchecked")
    public static void main(String args[])throws Exception{
        String ve =sc.nextLine();
        String tmp[] =ve.split(" ");
        int v = Integer.parseInt(tmp[0]);
        int e = Integer.parseInt(tmp[1]);
        data = new ArrayList[v+1];
        for(int i=0; i<e; i++){
            ve=sc.nextLine();
            tmp = ve.split(" ");
            data[Integer.parseInt(tmp[0])].add(new Dist(Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2])));
        }
        
        sc.close();
        out.close();
    }
}

