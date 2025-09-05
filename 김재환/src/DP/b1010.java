package DP;

import java.io.*;
import java.util.*;

public class b1010 {

	static int N, K;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			N = Math.max(x, y);
			K = Math.min(x, y);

//			System.out.println("N: " + N + " K: " + K);

			list = new ArrayList[N + 2];
			for (int i = 0; i <= N + 1; i++) {
				list[i] = new ArrayList<>();
			}

			for (int i = 0; i <= N + 1; i++) {
				f(i);
			}

			sb.append(list[N + 1].get(N - K)).append("\n");
		}
		
		System.out.println(sb);

	}

	private static void f(int n) {
		if (n == 0) {
			list[n].add(1);
			return;
		}
		if (n == 1) {
			list[n].add(1);
			list[n].add(1);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (i == 0 || i == n - 1) {
				list[n].add(1);
			} else {
				list[n].add(list[n - 1].get(i - 1) + list[n - 1].get(i));
			}
		}

	}

}
