import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Main{
    public static ArrayList<Elements> map[];
    public static int V, E, k, dist[], INF = 987654321;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int i, u, v, w;
        String line[] = in.readLine().split(" ");
        V = Integer.parseInt(line[0]);
        E = Integer.parseInt(line[1]);
        k = Integer.parseInt(in.readLine())-1;

        map = new ArrayList[V];
        dist = new int[V];
        java.util.Arrays.fill(dist, INF);

        for(i=0;i<V;i++) map[i] = new ArrayList<>();

        for(i=0;i<E;i++){
            line = in.readLine().split(" ");
            u = Integer.parseInt(line[0])-1;
            v = Integer.parseInt(line[1])-1;
            w = Integer.parseInt(line[2]);
            map[u].add(new Elements(v, w));
        }

        dijkstra();

        StringBuilder res = new StringBuilder();
        for(i=0;i<V;i++)
            res.append(dist[i]<INF ? dist[i]+"\n" : "INF\n");

        out.write(res.toString());
        out.close();
        in.close();
    }

    private static void dijkstra(){
        PriorityQueue<Elements> pq = new PriorityQueue<>();
        pq.offer(new Elements(k, dist[k] = 0));

        int cur, curWeight, next, nextWeight;

        while(!pq.isEmpty()){
            cur = pq.peek().x; curWeight = pq.poll().weight;
            for(Elements e : map[cur]){
                next = e.x; nextWeight = e.weight;

                if(dist[next] > dist[cur] + nextWeight){
                    dist[next] = dist[cur] + nextWeight;
                    pq.offer(new Elements(next, dist[next]));
                }
            }
        }
    }
}

class Elements implements Comparable<Elements>{
    int x, weight;
    public Elements(int x, int weight){
        this.x = x;
        this.weight = weight;
    }

    @Override
        public int compareTo(Elements anotherElements){
            return Integer.compare(weight, anotherElements.x);
        }
}
