package prac;

import java.io.*;
import java.util.*;

public class nextPermutation {
	// N개의 수를 입력받아 순서적으로 모두 나열하는 순열 생성


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		// step0: 전처리 - 가장 작은 순열 생성(오름차순 정렬)
		Arrays.sort(numbers);
		
		long start = System.nanoTime();
		
		do {
//			System.out.println(Arrays.toString(numbers));
		}while(np(numbers));
		
		long end = System.nanoTime();
		System.out.println((end-start)/1_000_000_000.0);

	}

	//
	private static boolean np(int[] numbers) { // 현 배열의 순열 상태에서 사전순 다음 순열 생성 후
		// 다음 순열이 존재하면 true, 아니면 false
		// numbers 배열의 상태가 여기서 직접적으로 바뀜

		// step1: 꼭대기 찾아 바로 앞 교환위치를 찾기
		int N = numbers.length;
		int i = N - 1;

		while (i > 0 && numbers[i - 1] >= numbers[i])
			i--;

		if (i == 0)
			return false; // 다음 순열 존재 X

		// step2: 교환 위치에 값을 크게 만들 뒤쪽 자신보다 큰 값 중 가작 작은 값 찾기
		int j = N - 1;

		while (numbers[i - 1] >= numbers[j])
			j--;

		// step3: i-1 위치값과 j의 위치값 교환

		swap(numbers, i-1, j);
		
		// step4: 꼭대기부터 뒤의 모든 순열을 가장 작은 형태로 만듦(오름차순 형태)
		int k = N - 1;
		while(i<k) swap(numbers, i++, k--);
		
		
		return true;

	}
	
	private static void swap(int[] numbers, int a, int b) {
		int tmp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = tmp;
	}
}
