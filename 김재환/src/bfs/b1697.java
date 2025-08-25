package bfs;

import java.io.*;
import java.util.*;

public class b1697 {

	static int ans = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		bfs(N, M);
	}

	private static void bfs(int s, int e) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[200000 + 1];
		int[] cnt = new int[200000 + 1];

		visited[s] = true;
		queue.offer(s);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
//			System.out.println(cnt[cur] + "번째: " + cur);
			if (cur == e) {
				System.out.println(cnt[cur]);
				return;
			}

			if (cur + 1 <= 200000 && !visited[cur + 1]) {
				queue.offer(cur + 1);
				visited[cur + 1] = true;
				cnt[cur + 1] = cnt[cur] + 1;
			}
			if (cur - 1 >= 0 && !visited[cur - 1]) {
				queue.offer(cur - 1);
				visited[cur - 1] = true;
				cnt[cur - 1] = cnt[cur] + 1;
			}
			if (cur * 2 < 200000 && !visited[cur * 2]) {
				queue.offer(cur * 2);
				visited[cur * 2] = true;
				cnt[cur * 2] = cnt[cur] + 1;
			}

		}
	}

}
