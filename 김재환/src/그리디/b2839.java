package 그리디;

import java.io.*;
import java.util.*;

public class b2839 {

	static int MIN = 5000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		greedy(N, -1, 0);

		if (MIN != 5000)
			System.out.println(MIN);
		else
			System.out.println(-1);
	}

	static void greedy(int n, int cnt, int b) {
		if (n < 0)
			return;
		System.out.println("left: " + n + " cnt: " + cnt);
		if (n == 0 && MIN > cnt) {
			MIN = cnt;
			System.out.println("MIN change: " + MIN + " " + cnt);
			return;
		}
		if (n == 0)
			return;

		greedy(n - b, cnt + 1, 3);
		greedy(n - b, cnt + 1, 5);
	}

}
