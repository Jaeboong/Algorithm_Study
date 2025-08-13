package prac;

import java.io.*;
import java.util.*;

public class newPermutation {

	static int N, R;
	static int[] input;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		numbers = new int[R];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		long start = System.nanoTime();
		permutation(0, 0);
		long end = System.nanoTime();
//		System.out.println((end-start)/1_000_000_000.0);
	}

	static void permutation(int cnt, int flag) {
		if(cnt==R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i=0; i<N; i++) {
			if ((flag&1<<i) != 0) continue;
			numbers[cnt] = input[i];
			permutation(cnt+1, flag | 1<<i);
		}
	}

}
