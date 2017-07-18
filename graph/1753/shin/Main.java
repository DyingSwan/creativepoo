import java.util.*;

public class Main {
   static int INF = 987654321;
   static int VERTEX;
   static int EDGE;
   static int START_VERTEX;
   static int[] result; // ?몄젒 由ъ뒪??
   public static ArrayList<ArrayList<Vertex>> map = new ArrayList<>(); // ?곗꽑?쒖쐞 ??

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      VERTEX = input.nextInt();
      EDGE = input.nextInt();
      START_VERTEX = input.nextInt();
      result = new int[VERTEX];
      java.util.Arrays.fill(result, INF);
      for (int i = 0; i < VERTEX; i++) {
         map.add(new ArrayList<>());
      }
      for (int i = 0; i < EDGE; i++) {
         int VOne = input.nextInt() - 1;
         int VTwo = input.nextInt() - 1;
         int dist = input.nextInt();
         map.get(VOne).add(new Vertex(VTwo, dist));
      }
      wayDist(START_VERTEX - 1);
      for (int i = 0; i < VERTEX; i++) {
         if (result[i] < INF)
            System.out.println(result[i]);
         else
            System.out.println("INF");
      }
   }

   public static void wayDist(int start) {
      PriorityQueue<Vertex> q = new PriorityQueue<>();
      result[start] = 0;
      q.offer(new Vertex(start, 0));

      while (!q.isEmpty()) {
         int cost = q.peek().getDist();
         int curV = q.poll().getNode();
         for (int i = 0; i < VERTEX; i++) {
            ArrayList<Vertex> temp = map.get(curV);
            for (int j = 0; j < temp.size(); j++) {
               int index = temp.get(j).getNode();
               if (result[index] > result[curV] + temp.get(j).getDist()) {
                  result[index] = temp.get(j).getDist() + result[curV];
                  q.offer(new Vertex(index, result[index]));
               }
            }
         }
      }
   }
}

class Vertex implements Comparable<Vertex> {
   int node;
   int dist;

   public Vertex(int Node, int Dist) {
      this.dist = Dist;
      this.node = Node;
   }

   public int getDist() {
      return dist;
   }

   public int getNode() {
      return node;
   }

   @Override
   public int compareTo(Vertex nextNode) {
      if (this.dist > nextNode.getDist())
         return 1;
      else if (this.dist == nextNode.getDist())
         return 0;
      else
         return -1;
   }
}
