package DP;

import java.io.*;
import java.util.*;

public class b17069 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		long[][][] dp = new long[N + 1][N + 1][3];

		final int R = 0;
		final int RD = 1;
		final int D = 2;

		dp[1][2][0] = 1;

		for (int r = 1; r <= N; r++) {
			for (int c = 3; c <= N; c++) {
				if (map[r][c] != 1) {
					dp[r][c][R] = dp[r][c - 1][R] + dp[r][c - 1][RD];
				}
				if (map[r][c] != 1 && map[r - 1][c] != 1 && map[r][c - 1] != 1) {
					dp[r][c][RD] = dp[r - 1][c - 1][R] + dp[r - 1][c - 1][RD] + dp[r - 1][c - 1][D];
				}
				if (map[r][c] != 1) {
					dp[r][c][D] = dp[r - 1][c][RD] + dp[r - 1][c][D];
				}

			}
		}

		System.out.println(dp[N][N][R] + dp[N][N][RD] + dp[N][N][D]);
	}

}
