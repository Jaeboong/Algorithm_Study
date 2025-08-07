package prac;

import java.util.Arrays;

public class nPr {
	static int n=3, r=2;
	static int[] numbers;//맨 앞부터 차례대로 뽑힌 수 저장
	static boolean[] isSelected; //각 수 들이 뽑혔는지 상태 플래그 저장 (1: 숫자1 선택여부, 2: ...)
	
	public static void main(String[] args) {
		numbers= new int[r];
		isSelected = new boolean[n+1];
		permutation(0);
	}
	
	public static void permutation(int cnt) { //해당 자리에 들어갈 수 순열로 뽑기
									   // cnt : 이전까지 뽑은 수들의 개수
		
		if(cnt==r) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		//가능한 모든 수를 해당 자리에 시도
		for(int i=1; i<=n; i++) {
			//이미 선택된 수는 패스
			if(isSelected[i]) continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
}
