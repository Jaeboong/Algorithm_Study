package 완전탐색;

import java.io.*;
import java.util.*;

public class b3109 {

	static int R, C;
	static char[][] rc;
	static int ans = 0;

	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		rc = new char[R][C];

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				rc[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(rc[i][j]);
			}
			System.out.println();
		}

		for (int i = 0; i < R; i++) {
			if (dfs(i, 0)) {
//				System.out.println("ans++");
				ans++;
			}
		}

		System.out.println(ans);

	}

	static boolean dfs(int r, int c) {
		
		if(c == C-1) {
			return true;
		}

		rc[r][c] = 'x';
		
//		System.out.println("dfs 시작: " + r);

		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (0 <= nr && nr < R && 0 <= nc && nc < C)
			if (0 <= nr && nr < R && 0 <= nc && nc < C && rc[nr][nc] == '.') {
				
				if (dfs(nr, nc)) {
					return true;
				}
			}
		}

		return false;
	}

}
