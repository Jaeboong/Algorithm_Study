package LIS;

import java.io.*;
import java.util.*;

public class s3307 {

	static int[] arr;
	static ArrayList<Integer> ans;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();

		for (int testcase = 1; testcase <= T; testcase++) {
			int N = sc.nextInt();
			arr = new int[N];
			ans = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			
			for(int i=0; i<N; i++) {
				binarySearch(arr[i]);
			}
			
			sb.append("#").append(testcase).append(" ").append(ans.size()).append("\n");
		}
		System.out.println(sb);
	}

	private static void binarySearch(int target) {
		int left = 0;
		int right = ans.size();
			
		while(left < right) {
			int mid = left + (right - left) / 2;
			if(ans.get(mid) < target) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		int pos = left;
		
		if(pos >= ans.size()) {
			ans.add(target);
		}
		else {
			ans.set(pos, target);
		}
	}
	
//	private static void lis(int target) {
//		if(ans.isEmpty()) {
//			ans.add(target);
//		}
//		
//		int pos = binarySearch(target);
//		
//		if(pos >= ans.size()) {
//			ans.add(target);
//		}
//		else {
//			ans.set(pos, target);
//		}
//	}

}
