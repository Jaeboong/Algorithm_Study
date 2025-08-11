package backtracking;

import java.io.*;
import java.util.*;

public class b2961 {

	static int n;
	static boolean[] isSelect;
	static int[][] sb;
	static int min = 999999999;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		StringTokenizer st;

		sb = new int[n][2];
		isSelect = new boolean[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			sb[i][0] = Integer.parseInt(st.nextToken());
			sb[i][1] = Integer.parseInt(st.nextToken());

		}

		comb(0, 0);
		System.out.println(min);
	}

	static void comb(int cnt, int s) {
//		System.out.println("cnt: " + cnt + " \nisSelect: " + Arrays.toString(isSelect));
		int sumS = 1;
		int sumB = 0;
		for (int i = 0; i < n; i++) {
			if (isSelect[i]) {
				System.out.println("\nSelect: " + sb[i][0] + ", " + sb[i][1]);
				sumS *= sb[i][0];
				sumB += sb[i][1];
				System.out.println("sumS: " + sumS + "  sumB: " + sumB);
			}
		}
		if (min > Math.abs(sumS - sumB)) {
			min = Math.abs(sumS - sumB);
			System.out.println("min update: " + min);
		}

		for (int i = s; i < n; i++) {
			isSelect[i] = true;
			comb(cnt + 1, i + 1);
			isSelect[i] = false;
		}
	}

}
