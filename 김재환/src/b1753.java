import java.io.*;
import java.util.*;

public class b1753 {

	static int INF = Integer.MAX_VALUE;
	static int V, E, start;
	static ArrayList<Integer>[] vert;
	static ArrayList<Integer>[] cost;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());

		vert = new ArrayList[V + 1];
		cost = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			vert[i] = new ArrayList<>();
			cost[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			vert[u].add(v);
			cost[u].add(w);
		}

		dijkstra();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF) {
				sb.append("INF\n");
			} else {
				sb.append(dist[i]).append("\n");
			}
		}

		System.out.println(sb);
	}

	private static void dijkstra() {
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		visited = new boolean[V + 1];

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] { start, 0 });
		System.out.println("start: " + Arrays.toString(pq.peek()));
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int v = cur[0];
			int d = cur[1];
			System.out.println("vert, dist: " + Arrays.toString(cur));
			if(visited[v]) {
				System.out.println(v + ": visited, pass");
				continue;
			}
			visited[v] = true;

			for (int i = 0; i < vert[v].size(); i++) {
				int nv = vert[v].get(i);
				int nc = cost[v].get(i);
				int nd = d + nc;

				if (!visited[nv] && nd < dist[nv]) {
					System.out.println(nd + " < " + dist[nv]);
					dist[nv] = nd;
					pq.add(new int[] { nv, nd });
				}
			}
		}
	}
}
