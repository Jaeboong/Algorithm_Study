package LIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b1965 {

	static int[] arr;
	static ArrayList<Integer> dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		dp = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int x : arr) {
			lis(x);
		}
		
		System.out.println(dp.size());

	}

	private static int binarySearch(int target) {
		int left = 0;
		int right = dp.size();

		while (left < right) {
			int mid = left + (right - left) / 2;

			if (dp.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}
	
	private static void lis(int target) {
		if(dp.isEmpty()) {
			dp.add(target);
			return;
		}
		
		int pos = binarySearch(target);
		
		if(pos >= dp.size()) {
			dp.add(target);
		}
		else {
			dp.set(pos, target);
		}
	}

}
