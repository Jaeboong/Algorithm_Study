package prac;

import java.util.Arrays;
import java.util.Scanner;

public class DiceTest {

	static int N, numbers[], totalCnt;
	
	static boolean[] isSelected;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int M = sc.nextInt();

		numbers = new int[N];

		switch (M) {
		case 1:  // 중복 순열
			dice1(0);
			break;
		case 2:  // 순열
			isSelected = new boolean[7]; // 주사위눈 1~6
			dice2(0);
			break;
		default:
			System.out.println("error");
			break;
		}
		System.out.println("모든 경우의 수: "+ totalCnt);
	}
	
	public static void dice1(int cnt) { // cnt: 기존까지 던져진 주사위 횟수
		//기저 파트
		if(cnt==N) {
			++totalCnt;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		//해당 자리에 가능한 주사위 눈 모두 시도
		for(int i=1; i<=6; i++) {
			numbers[cnt] = i;
			dice1(cnt+1);
		}
	}
	
	public static void dice2(int cnt) { // cnt: 기존까지 던져진 주사위 횟수
		//기저 파트
		if(cnt==N) {
			++totalCnt;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		//해당 자리에 가능한 주사위 눈 모두 시도
		for(int i=1; i<=6; i++) {
			//앞에서 나온 주사위 눈인지 체크
			if(isSelected[i]) continue;
			//아니라면 주사위눈 선택
			numbers[cnt] = i;
			isSelected[i] = true;
			//다음 주사위 던지러 가기
			dice2(cnt+1);
			//다음 주사위눈 바꿔서 처리하기 위해 선택을 되돌림!
			isSelected[i] = false;
		}
	}

}
