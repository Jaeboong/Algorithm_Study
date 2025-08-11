package backtracking;

import java.util.*;
import java.io.*;

public class b1325 {
	static int n;
	static int m;
	static ArrayList<Integer>[] rev;
	static int[] visited;
	static int[] reach;
	static int runId = 1;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		rev = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			rev[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			rev[c2].add(c1);
		}

		visited = new int[n + 1];
		reach = new int[n + 1];
		int max = 0;

		for (int s = 1; s <= n; s++) {
			reach[s] = bfs(s);
			if (reach[s] > max)
				max = reach[s];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			if(reach[i] == max) sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	static int bfs(int s) {
//		System.out.println(s + "번째에서 bfs 시작");
		ArrayDeque<Integer> q = new ArrayDeque<>();

		visited[s] = runId;
		q.add(s);
//		System.out.println("시작 q: " + q.peek());
		int cnt = 1;

		while (!q.isEmpty()) {
//			System.out.println("q 꺼내기: " + q.peek());
			int u = q.poll();
//			System.out.println(u + "를 신뢰하는 컴퓨터: " + rev[u]);
			for (int v : rev[u]) {
//				System.out.println(Arrays.toString(visited));
				if (visited[v] != runId) {
//					System.out.println("visited[" + v + "]: " + visited[v] + " runId: " + runId);
					visited[v] = runId;
					q.add(v);
					cnt++;
//					System.out.println("방문 안한 곳 " + runId + "추가");
//					System.out.println(q);
				}
			}
		}
		runId++;
//		System.out.println("종료 " + runId);
		return cnt;
	}
}
