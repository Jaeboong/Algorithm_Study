package backtracking;

import java.util.*;
import java.io.*;

public class b3040 {
	static boolean[] visit = new boolean[9];
	static int[] num= new int[9];
	static int[] arr = new int[7];
	public static void main(String[] args) throws Exception {
		/*
		 * 난쟁이 9명중 가짜난쟁이 2명 찾아 죽이기
		 * 어떤새끼일까?
		 * 중복따윈 없다. 순서도 고려할 필요가 없다.
		 * 9C7 = 9C2 조합이다.
		 * 이 문제가 조합인데 왜 브론즈일까
		 * 나으 페어 상훈님 피셜 9 난쟁이라 아니라 90만 난쟁이였으면 백트래킹을 해야하고
		 * 이중 포문따위 돌 수 없는 어려운 문제였을 것이다.
		 * 
		 * 근데 나는 이중포문 따위 쓰지 않을것이다. 난 백트래킹으로 풀겠다.
		*/
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; i++) num[i] = Integer.parseInt(br.readLine());
		
		dfs(0, 0);
	}
	
	static void dfs(int cnt, int s) {
		//기저조건 설정
		if(cnt == 7) {
			int sum = 0;
			for(int i=0; i<7; i++) sum+=arr[i];
			if(sum == 100) for(int a : arr) System.out.println(a);
			return;
		}
		
		for(int i=s; i<9; i++) {
			arr[cnt] = num[i];
			dfs(cnt+1, i+1);
		}
	}

}
