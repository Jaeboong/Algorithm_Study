package prac;

import java.util.Scanner;

public class SubsetTest {

	static int N; // 원소 개수
	static int[] input; // 입력 값
	static boolean[] isSelected; // 부분 집합 안에 속하는지 여부

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		generateSubset(0, 0);
	}

	// 2. cnt: 다룬 원소 개수, setCnt: 부분집합을 구성하는 원소 개수
	private static void generateSubset(int cnt, int setCnt) {
		//기본파트
		if (cnt == N) {
			System.out.print("부분집합의 원소 개수: " +setCnt+ " ==> ");
			for (int i = 0; i < N; i++) { // 부분 집합의 구성 요소만 뽑기
				System.out.print((isSelected[i] ? input[i] : "X") + " ");
			}
			System.out.println();
			return;
		}

		// 1. 해당 원소 부분집합에 넣기
		isSelected[cnt] = true;
		generateSubset(cnt + 1, setCnt + 1);
		isSelected[cnt] = false;
		generateSubset(cnt + 1, setCnt);
	}
}
