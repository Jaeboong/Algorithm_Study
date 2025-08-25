package prac;

import java.util.*;
import java.io.*;

public class bfs2 {

	static int V;// 정점수
	static int[][] adjMatrix; // 인접행렬

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("Test2.txt"));
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		int E = sc.nextInt();
		adjMatrix = new int[V][V];
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[from][to] = 1;
//            adjMatrix[to][from] = 1;
		}

//        for(int i = 0; i < V; i++) {
//            System.out.println(Arrays.toString(adjMatrix[i]));
//        }
		bfsWithBreadth2(0);
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[V];
		
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println((char)(current+65));
			for (int i = 0; i < V; i++) {
				if(adjMatrix[current][i] != 0 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}


	private static void bfsWithBreadth(int start) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[V];

		queue.offer(new int[] { start, 0 });
		visited[start] = true;
//    	int breadth = 0; // 이렇게 할 수 도 있고

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			System.out.println((char) (current[0] + 65) + ":" + current[1]);
			for (int i = 0; i < V; i++) {
				if (adjMatrix[current[0]][i] != 0 && !visited[i]) {
					queue.offer(new int[] { i, current[1] + 1 });
					visited[i] = true;

				}
			}
		}
	}

	private static void bfsWithBreadth2(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[V];

		queue.offer(start);
		visited[start] = true;
		int breadth = 0;

		while (!queue.isEmpty()) {
			int qSize = queue.size();
			while (--qSize >= 0) {
				int current = queue.poll();
				System.out.println((char) (current + 65) + ":" + breadth);
				for (int i = 0; i < V; i++) {
					if (adjMatrix[current][i] != 0 && !visited[i]) {
						queue.offer(i);
						visited[i] = true;

					}
				}
			}
			++breadth;
		}
	}
}