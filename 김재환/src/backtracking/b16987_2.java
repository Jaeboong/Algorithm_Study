package backtracking;

import java.io.*;
import java.util.*;

public class b16987_2 {
	static int N;
	static int[][] egg;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		egg = new int[N][2];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			egg[i][0] = Integer.parseInt(st.nextToken());
			egg[i][1] = Integer.parseInt(st.nextToken());
		}

		eggFight(0);
		System.out.println(max);

	}

	static void eggFight(int cnt) {
//		System.out.println(cnt + "번째 계란으로 계란치기 시작");
		if (cnt == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (egg[i][0] <= 0) {
//					System.out.println(egg[i][0] + " 찾았다");
					sum++;
				}

			}
			max = Math.max(max, sum);
			return;
		}
//들고있는 계란 깨지면 패스
		if (egg[cnt][0] <= 0) {
			eggFight(cnt + 1);
			return;
		}
//만약 내가 든 계란과 깰 계란의 인덱스가 같거나 타겟 계란이 이미 모두 깨져있으면 
		boolean target = false;

		for (int i = 0; i < N; i++) {
			if (egg[i][0] <= 0 || i == cnt)
				continue;

			target = true;
//			System.out.println("egg[cnt][0]: " + egg[cnt][0] + " egg[i][0]: " + egg[i][0]);
			egg[cnt][0] -= egg[i][1];
			egg[i][0] -= egg[cnt][1];
//			System.out.println("egg[cnt][0]: " + egg[cnt][0] + " egg[i][0]: " + egg[i][0]);
			eggFight(cnt + 1);

			egg[cnt][0] += egg[i][1];
			egg[i][0] += egg[cnt][1];

		}

		if (!target)
			eggFight(cnt + 1);
// 박치기 후 cnt+1번 째 계란을 짚고 다음 계란과 맞짱을 깐다.
	}
}