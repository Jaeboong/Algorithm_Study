package 그리디;

import java.io.*;
import java.util.*;

public class b1931 {
	static int N;
	static int[][] arr;
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		
		int ans = 0;
		int s = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, (o1, o2) -> {
			if(o1[1] == o2[1]) {
				return o1[0] - o2[0];
			}
			return o1[1] - o2[1];
			
		});
//		for(int i=0; i<N; i++) {
//			System.out.print(arr[i][0] + " ");
//			System.out.println(arr[i][1]);
//		}

		for(int i=0; i<N; i++) {
			if(arr[i][0] >= s) {
				ans++;
				s = arr[i][1];
			}
		}
			
		
		System.out.println(ans);
		
	}
}
