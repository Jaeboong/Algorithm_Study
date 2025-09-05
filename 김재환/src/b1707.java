import java.io.*;
import java.util.*;

public class b1707 {
	static ArrayList<Integer>[] list;
	static char[] color;
	static int V;
	static int E;
	static boolean ans = true;
	
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			ans = true;
			
			color = new char[V+1];
			Arrays.fill(color, 'w');
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
			
			for(int i=1; i<=V; i++) {
				//System.out.println(Arrays.toString(color));
				if(color[i] == 'w') {
					dfs(i, 'r');
				}
			}
			
			if(ans) {
				sb.append("YES").append("\n");
			}
			else {
				sb.append("NO").append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int v, char c) {
		color[v] = c;
		for(int i : list[v]) {
			if(color[v] == color[i]) {
				ans = false;
				return;
			}
			if(color[i] == 'w') {
				dfs(i, color[v] == 'r' ? 'b' : 'r');
			}
			
		}   
	}
}
