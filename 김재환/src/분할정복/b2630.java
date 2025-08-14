package 분할정복;

import java.io.*;
import java.util.*;

public class b2630 {
	static int N;
	static int[][] NN;
	static int wCnt = 0;
	static int bCnt = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		NN = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				NN[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divide(0, 0, N);
		System.out.println(wCnt);
		System.out.println(bCnt);

	}

	static void divide(int x, int y, int n) {

		System.out.println();
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				System.out.print(NN[i][j] + " ");
			}
			System.out.println();
		}

		// return condition
		if (n == 1) {
			if (NN[y][x] == 0) {
				wCnt++;
				System.out.println("white");
			} else {
				bCnt++;
				System.out.println("blue");
			}
			return;
		}
		int init = NN[y][x];
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (i == y + n - 1 && j == x + n - 1 && (init == NN[i][j])) {
					if (NN[y][x] == 0) {
						wCnt++;
						System.out.println("white");
					} else {
						bCnt++;
						System.out.println("blue");
					}
				}
				if (init != NN[i][j]) {
					System.out.println("init: " + init + " now[" + i + "][" + j + "]: " + NN[i][j]);
					divide(x, y, n / 2);
					divide(x + n / 2, y, n / 2);
					divide(x + n / 2, y + n / 2, n / 2);
					divide(x, y + n / 2, n / 2);
					return;
				}
			}
		}
	}
}
