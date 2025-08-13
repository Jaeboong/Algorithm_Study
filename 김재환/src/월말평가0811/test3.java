package 월말평가0811;

import java.io.*;
import java.util.*;

public class test3 {
	
	/*bfs로 접근해서 풀려고 했던 문제인데
	 * 
		
	 
	 
	 */

	static int N;
	static int M;
	static List<Integer>[] arr;
	static boolean[] visit;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		visit = new boolean[N + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}
		
//		System.out.println(Arrays.toString(arr));

		for (int i = 1; i <= N; i++) {
			if (visit[i]) {
//				System.out.println(i + " 는 이미 방문한 서버");
				continue;
			}
//			System.out.println(i + "는 미방문 서버, 그룹 추가");
			cnt++;
			bfs(i);
		}

		System.out.println(cnt);
	}

	static void bfs(int s) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
//		System.out.println(s + "랑 연결된 서버 탐색");
		visit[s] = true;
		q.add(s);

		while (!q.isEmpty()) {
//			System.out.println("\n현재 큐 상태: " + q);
			int curr = q.poll();
			
			for (int i : arr[curr]) {
//				System.out.println("현재 arr[s]는 " + i);
				if (!visit[i]) {
//					System.out.println(i + ": 연결 확인");
					visit[i] = true;
//					System.out.println(Arrays.toString(visit));
					q.add(i);
				}
			}
		}
		
//		System.out.println("탐색종료\n");
	}

}
