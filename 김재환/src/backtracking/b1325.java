package backtracking;

import java.util.*;
import java.io.*;

public class b1325 {
	static int n;
	static int m;
	static boolean[][] r;
	static int max = 0;
	static List<Integer> result = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = new boolean[n+1][n+1];
		int total = 0;
		
		for(int i=0; i<m; i++) {
			st=new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			r[c2][c1] = true;
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
//				if(r[i][j]) System.out.println(j + "번 컴퓨터는 " + i + "번 컴퓨터를 신뢰하는가? " + r[i][j]);
			}
		}
		
		dfs(1, 1, 0);
		System.out.println(result);
		
	}
	
	static void dfs(int s, int j, int sum) {
		int cnt = 0;
		
		System.out.println("start search");
		
		for(int k=1; k<=n; k++) {
			if(r[j][k]) {
				System.out.println(k + "번 컴퓨터는 " + j + "번 컴퓨터를 신뢰합니다.");
				dfs(s, k, sum+1);
				cnt++;
			}
		}
		
		if (cnt == 0) {
			System.out.println("더 이상 " + j + "번 컴퓨터를 신뢰하는 컴퓨터는 없습니다.");
			if(max <= sum) {
				System.out.println(s + "번 컴퓨터는 " + sum + "만큼 신뢰를 받습니다.");
				max = sum;
				result.add(s);
			}
			return;
		}
		
	}

}
