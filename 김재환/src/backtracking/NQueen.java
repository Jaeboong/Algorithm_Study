package backtracking;

import java.io.*;
import java.util.*;

public class NQueen {

	static int N, ans;
	static boolean[] col, slash, bSlash;

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new boolean[N + 1];
		slash = new boolean[2 * N + 1];
		bSlash = new boolean[2 * N];
		
		ans = 0;
		setQueen(1);
		System.out.println(ans);
		
	}

	static void setQueen(int row) {
		// 기저 파트
		// 가지치기
		if (row > N) {
			++ans;
			return;
		}

		// 유도 파트
		for (int c = 1; c <= N; c++) {
			if (col[c] || slash[row + c] || bSlash[row - c + N])
				continue;
			// 퀸 배치 내용을 자료구조에 기록
			col[c] = slash[row + c] = bSlash[row - c + N] = true;
			// 다음 퀸 놓기
			setQueen(row + 1);
			col[c] = slash[row + c] = bSlash[row - c + N] = false;
		}
	}

}
