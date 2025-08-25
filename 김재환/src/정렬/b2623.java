package 정렬;

import java.io.*;
import java.util.*;

public class b2623 {
	
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] inD = new int[N+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		ArrayList<Integer>[] list;
		list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			for(int j=1; j<cnt; j++) {
				int to = Integer.parseInt(st.nextToken());
				list[from].add(to);
				from = to;
				inD[to]++;
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(inD[i] == 0) {
				q.offer(i);
			}
		}
		List<Integer> res = new ArrayList<>();
		while(!q.isEmpty()) {
			int idx = q.poll();
			
			res.add(idx);
			for(int i : list[idx]) {
				inD[i]--;
				if(inD[i] == 0) {
					q.offer(i);
				}
			}
		}
		if(res.size() != N) {
			System.out.println(0);
			return;
		}
		
		for(int ans : res) {
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

}
