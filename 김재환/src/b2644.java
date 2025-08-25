import java.util.*;
import java.io.*;

public class b2644 {

	static int N;
	static ArrayList[] people;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		people = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			people[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		int t1 = Integer.parseInt(st.nextToken());
		int t2 = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			people[x].add(y);
			people[y].add(x);
		}

//		System.out.println(Arrays.toString(people));

		bfs(t1, t2);
	}

	private static void bfs(int start, int end) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);

		boolean[] visited = new boolean[N + 1];
		int[] distance = new int[N + 1];
		visited[start] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			ArrayList<Integer> next = people[cur];
//			System.out.println("current: " + cur + " : " + next + " cnt: " + cnt);
			for (int i = 0; i < next.size(); i++) {
				if (next.get(i) == end) {
					System.out.println(distance[cur]+1);
					return;
				}
					
				if (!visited[next.get(i)]) {
					visited[next.get(i)] = true;
					distance[next.get(i)] = distance[cur] + 1;
					queue.offer(next.get(i));
				}
					
			}

			cnt++;
		}
		
		System.out.println(-1);
	}

}
