import java.io.*;
import java.util.*;

public class b2458 {
	
	private static final int ArrayDeque = 0;
	static ArrayList<Integer>[] r1;
	static ArrayList<Integer>[] r2;
	static int ans = 0;
	static int cnt = 0;
	
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		r1 = new ArrayList[N+1];
		r2 = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			r1[i] = new ArrayList();
			r2[i] = new ArrayList();
		}
		
		for (int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			r1[from].add(to);
			r2[to].add(from);
		}
		
		for(int i=1; i<=N; i++) {
			cnt = 0;
			bfs1(i);
			bfs2(i);
//			System.out.println(i+"번째: " + cnt);
			if(cnt == N-1) {
				ans++;
			}
		}
		
		System.out.println(ans);
		
	}
	
	private static void bfs1(int start) {
		Deque<Integer> deque = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		deque.offer(start);
		visited[start] = true;
		
		while(!deque.isEmpty()) {
			int cur = deque.poll();
			
			for(int next: r1[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					deque.offer(next);
					cnt++;
				}
			}
		}
	}
	
	private static void bfs2(int start) {
		Deque<Integer> deque = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		deque.offer(start);
		visited[start] = true;
		
		while(!deque.isEmpty()) {
			int cur = deque.poll();
			
			for(int next: r2[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					deque.offer(next);
					cnt++;
				}
			}
		}
	}
}

