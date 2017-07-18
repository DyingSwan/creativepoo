import java.util.*;

public class Main {
	static int INF = 987654321;
	static int VERTEX;
	static int EDGE;
	static int START_VERTEX;
	static int[] result; // 결과값이 저장되는 배열
	public static ArrayList<Vertex[]> map = new ArrayList<Vertex[]>(); // 인접 리스트
																		// 구현
																		// ??

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		VERTEX = input.nextInt();
		EDGE = input.nextInt();
		START_VERTEX = input.nextInt();
		result = new int[VERTEX];
		Vertex[] temp = new Vertex[VERTEX];
		java.util.Arrays.fill(temp, new Vertex());
		// java.util.Arrays.fill(result, INF);
		for (int i = 0; i < VERTEX; i++) {
			map.add(temp);
			result[i] = INF;
			// for (int j = 0; j < VERTEX; j++)
			// map.get(i)[j] = temp;
		}
		for (int i = 0; i < EDGE; i++) {
			int VOne = input.nextInt() - 1;// 시작 종점
			int VTwo = input.nextInt() - 1;// 도착 종점
			int dist = input.nextInt();// 두 종점간 거
			map.get(VOne)[VTwo] = new Vertex(VTwo, dist);
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
		int index;
		int cost;
		int curV;

		while (!q.isEmpty()) {
			cost = q.peek().getDist();
			curV = q.poll().getNode();

			if (cost > result[curV])
				continue;

			// for (int i = 0; i < VERTEX; i++) {
			Vertex[] temp = map.get(curV);
			for (int j = 0; j < temp.length; j++) {
				index = temp[j].getNode();
				if (temp[j].getDist() != 0 && result[index] > result[curV] + temp[j].getDist()) {
					result[index] = temp[j].getDist() + result[curV];
					q.offer(new Vertex(index, result[index]));

				}
			}
			// }
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

	public Vertex() {
		this.node = 11;
		this.dist = 0;
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
