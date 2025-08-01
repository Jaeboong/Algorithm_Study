package 월말평가;

import java.io.*;
import java.util.*;

public class TEST2_7월 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Test2.txt"));;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int cnt = 0;
		while (cnt < T) {
			cnt++;
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] power = new int[n];
			for (int i = 0; i < n; i++) {
				power[i] = Integer.parseInt(st.nextToken());
			}
			
			int[][] arr = new int[n][2];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println(Arrays.deepToString(arr));
		}

	}

}
