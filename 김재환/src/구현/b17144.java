package 구현;

import java.io.*;
import java.util.*;

class air {
	public int r;
	public int c;

	public air(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

}

public class b17144 {

	static int R;
	static int C;
	static int T;
	static int[][] room;
	static int[][] tmp;
	static air a1 = null, a2 = null;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		room = new int[R][C];
		tmp = new int[R][C];
		int cnt = 0;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (cnt == 0 && room[i][j] == -1) {
					a1 = new air(i, j);
					cnt++;
				}
				if (cnt == 1 && room[i][j] == -1) {
					a2 = new air(i, j);
				}
			}
		}

		for (int i = 0; i < T; i++) {
			diffusion();
//			System.out.println("------------" + (i + 1) + "번 째 확산 후-------------");
//			for (int r = 0; r < R; r++) {
//				for (int c = 0; c < C; c++) {
//					System.out.print(room[r][c] + "\t");
//				}
//				System.out.println();
//			}
			airMove();

//			System.out.println("------------" + (i + 1) + "번 째 이동 후-------------");
//			for (int r = 0; r < R; r++) {
//				for (int c = 0; c < C; c++) {
//					System.out.print(room[r][c] + "\t");
//				}
//				System.out.println();
//			}

		}
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) { // 양수만 더함
					sum += room[i][j];
				}
			}
		}

		System.out.println(sum);
	}

	private static void diffusion() {

		tmp = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {

				if (j == 0 && (i == a1.r || i == a2.r)) {
					continue;
				}

				if (room[i][j] == 0) {
					continue;
				}
				int diffV = room[i][j] / 5;


				if (i + 1 < R) {
					if (!((i + 1 == a1.r && j == a1.c) || (i + 1 == a2.r && j == a2.c))) {
						tmp[i][j] -= diffV;
						tmp[i + 1][j] += diffV;
					}
				}
				if (i - 1 >= 0) {
					if (!((i - 1 == a1.r && j == a1.c) || (i - 1 == a2.r && j == a2.c))) {
//						System.out.println("아래 확산");
						tmp[i][j] -= diffV;
						tmp[i - 1][j] += diffV;
					}
				}
				if (j + 1 < C) {
					if (!((i == a1.r && j + 1 == a1.c) || (i == a2.r && j + 1 == a2.c))) {
//						System.out.println("오른쪽 확산");
						tmp[i][j] -= diffV;
						tmp[i][j + 1] += diffV;
					}
				}
				if (j - 1 >= 0) {
					if (!((i == a1.r && j - 1 == a1.c) || (i == a2.r && j - 1 == a2.c))) {
//					System.out.println("왼쪽 확산");
						tmp[i][j] -= diffV;
						tmp[i][j - 1] += diffV;
					}
				}
			}
		}
//		System.out.println();
//		System.out.println("---------------tmp 배열--------------");
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
//				System.out.print(tmp[i][j] + "\t");
				room[i][j] += tmp[i][j];
			}
//			System.out.println();
		}
	}

	private static void airMove() {
		// a1 down
		for (int r = a1.r - 1; r >= 0; r--) {
			if (r + 1 == a1.r) {
				room[r][0] = 0;
				continue;
			}
			down(r, 0);
		}
		// left
		for (int c = 1; c < C; c++) {
			left(0, c);
		}
		// a1 up
		for (int r = 1; r <= a1.r; r++) {
			up(r, C - 1);
		}
		// a2 up
		for (int r = a2.r + 1; r < R; r++) {
			if (r - 1 == a2.r) {
				room[r][0] = 0;
				continue;
			}
			up(r, 0);
		}
		// a2 left
		for (int c = 1; c < C; c++) {
			left(R - 1, c);
		}
		// a2 down
		for (int r = R - 2; r >= a2.r; r--) {
			down(r, C - 1);
		}
		// a2 right
		for (int c = C - 2; c > 0; c--) {
			right(a1.r, c);
			right(a2.r, c);
		}
	}

	private static void right(int r, int c) {
		int m = room[r][c];
		room[r][c] -= m;
		room[r][c + 1] += m;
	}

	private static void up(int r, int c) {
		int m = room[r][c];
		room[r][c] -= m;
		room[r - 1][c] += m;
	}

	private static void down(int r, int c) {
		int m = room[r][c];
		room[r][c] -= m;
		room[r + 1][c] += m;
	}

	private static void left(int r, int c) {
		int m = room[r][c];
		room[r][c] -= m;
		room[r][c - 1] += m;
	}

}
