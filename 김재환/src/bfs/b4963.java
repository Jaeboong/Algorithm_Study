package bfs;

import java.util.*;
import java.io.*;

public class b4963 {

	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		do {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];
			int cnt = 0;

			for (int i = 0; i < N; i++) {
				if (M > 1)
					st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					if (M > 1)
						map[i][j] = Integer.parseInt(st.nextToken());
					else
						map[i][j] = Integer.parseInt(br.readLine());
				}
			}
//			sb.append(Arrays.deepToString(map)).append("\n");

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);
						cnt++;
					}
				}
			}

			if (N != 0 && M != 0)
				sb.append(cnt).append("\n");
		} while (N != 0 && M != 0);
		System.out.println(sb);
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int[] dr = { 1, -1, 0, 0, 1, 1, -1, -1};
			int[] dc = { 0, 0, 1, -1, 1, -1, 1, -1 };

			for (int i = 0; i < 8; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (0 <= nr && nr < N && 0 <= nc && nc < M) {
					if (!visited[nr][nc] && map[nr][nc] == 1) {
						visited[nr][nc] = true;
						queue.offer(new int[] { nr, nc });
					}

				}
			}
		}

	}
}
