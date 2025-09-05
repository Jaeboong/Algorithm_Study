import java.io.*;
import java.util.*;

public class b1707 {
	static ArrayList<Integer>[] list;
	static char[] color;
	static int V;
	static int E;
	
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			color = new char[V+1];
			list = new ArrayList[V+1];
			for(int i=1; i<=V; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				list[u].add(v);
				list[v].add(u);
			}
		}
	}
	
	private static boolean dfs(int v) {
		color[v] = 'r';
		for(int i : list[v]) {
			if(color[i] == 'r') {
				return false;
			}
			if(color[i] == '\0') {
				color[i] = 'b';
			}
		}
	}
}
