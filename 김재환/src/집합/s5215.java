package 집합;

import java.io.*;
import java.util.*;

public class s5215 {

	static boolean[] isSelected;
	static int[][] arr;
	static int N;
	static int max;
	static int maxStfy;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			
			maxStfy = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			max = Integer.parseInt(st.nextToken());
			isSelected = new boolean[N];

			arr = new int[N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
//			System.out.println(Arrays.deepToString(arr));

			burger(0, 0, 0);

			System.out.print("#" + testcase + " " + maxStfy + "\n");
		}
	}

	static void burger(int cnt, int stfy, int cal) {
		if (cal > max) {
//			System.out.println(Arrays.toString(isSelected));
//			System.out.println("cal 초과");
			return;
		}

		if (cnt == N) {
			if (maxStfy < stfy)
				maxStfy = stfy;
			return;
		}
		
//		System.out.println(cnt +  "번째 cal: " + cal + " 만족도: " + stfy);

		isSelected[cnt] = true;
		burger(cnt + 1, stfy + arr[cnt][0], cal + arr[cnt][1]);
		isSelected[cnt] = false;
		burger(cnt + 1, stfy, cal);
	}

}