package LIS;

import java.util.Arrays;

public class prac {

	public static void main(String[] args) {
		int[] arr = { 3, 2, 6, 4, 5, 1 };
		int n = arr.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		int maxLen = 1;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}
		System.out.println("LIS 길이 = " + maxLen);

	}

}
