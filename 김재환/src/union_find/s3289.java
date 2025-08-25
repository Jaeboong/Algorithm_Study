package union_find;

import java.io.*;
import java.util.*;

public class s3289 {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			sb.append("#").append(testcase).append(" ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if(cmd == 0) {
					union(x, y);
				}
				else {
					if(find(x) == find(y)) {
						sb.append(1);
					}
					else {
						sb.append(0);
					}
				}
					
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static int find(int x) {
		if(parent[x] != x) {
			int root = find(parent[x]);
			parent[x] = root;
			
			return root;
		}
		
		return x;
	}
	
	private static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if(rootX == rootY) {
			return;
		}
		
		parent[rootX] = rootY;
		return;
	}

}
