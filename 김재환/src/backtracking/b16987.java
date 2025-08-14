package backtracking;

import java.io.*;
import java.util.*;

public class b16987 {

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
		// 기저파트
		// 지금 들고있는 계란이 깨져있거나, 제일 오른쪽 계란이면 패스
		// 우선 제일 오른쪽 계란이면 끝.
		if (cnt == N) {
//			System.out.println(cnt + "번째 계란 탐색 완료 집계중....");
			int count = 0;
			for(int i=0; i<N; i++) {
				if (egg[i][0] <= 0) count++; 
			}
			if (max < count) {
				max = count;
//				System.out.println("최고점 계란치기 찾았다!! " + max + "가 최고점~");
			}
			return;
		}

		
		if(egg[cnt][0] <= 0) {
			eggFight(cnt+1);
			return;
		}
		// 유도 파트
		// cnt번 째 계란으로 i번째 계란을 친다.
		// i는 cnt와 같으면 안된다.
		// 서로 박치기
		
		boolean targetE = false;
		
		for (int i = 0; i < N; i++) {
			
			if (i == cnt || egg[i][0] <= 0) {
//				if(egg[i][0] < 0) System.out.println(cnt + "번째 계란 탐색중 " + i + "번 계란은 이미 깨져있다.");
				continue;
			}
			
			targetE = true;
			
			egg[cnt][0] -= egg[i][1];
			egg[i][0] -= egg[cnt][1];
			
			eggFight(cnt+1);
			
			egg[cnt][0] += egg[i][1];
			egg[i][0] += egg[cnt][1];
		}
		
		if(!targetE) {
			eggFight(cnt + 1);
		}

		// 박치기 후 cnt+1번 째 계란을 짚고 다음 계란과 맞짱을 깐다.
	}

}
