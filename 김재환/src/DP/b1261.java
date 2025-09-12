package DP;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	int x, y, cost;

	public Node(int x, int y, int cost) {
		super();
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	public int compareTo(Node other) {
		return Integer.compare(this.cost, other.cost);
	}
}

public class b1261 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] MN = new int[M][N];
		int[][] cost = new int[M][N];

		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				MN[i][j] = str.charAt(j) - '0';
				cost[i][j] = Integer.MAX_VALUE;
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		cost[0][0] = 0;

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		boolean[][] visited = new boolean[M][N];

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			int x = curr.x;
			int y = curr.y;
			int c = curr.cost;

			if (c > cost[x][y])
				continue;

			if (visited[x][y])
				continue;
			visited[x][y] = true;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
					continue;
				}

				int newCost = c + MN[nx][ny];

				if (newCost < cost[nx][ny]) {
					cost[nx][ny] = newCost;
					pq.offer(new Node(nx, ny, newCost));
				}
			}
		}

		System.out.println(cost[M - 1][N - 1]);
	}
}
