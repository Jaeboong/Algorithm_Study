package 구간합;

import java.util.*;
import java.io.*;

public class b11660 {

	public static void main(String[] args) throws Exception {
		// 큰 사각형 - (작은사각1 + 작은사각2 - 겹친사각)

		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] PS = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				PS[i][j] = PS[i - 1][j] + PS[i][j - 1] - PS[i - 1][j - 1] + Integer.parseInt(st.nextToken());
			}
		}

		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			int i1 = Integer.parseInt(st.nextToken());
			int j1 = Integer.parseInt(st.nextToken());

			int i2 = Integer.parseInt(st.nextToken());
			int j2 = Integer.parseInt(st.nextToken());
//			System.out.println("PS["+i2+"]["+j2+"] : " + PS[i2][j2] + 
//					"\nPS["+i2+"]["+j1+"] : " + PS[i2][j1] +
//					"\nPS["+i1+"]["+j2+"] : " + PS[i1][j2] +
//					"\nPS["+i1+"]["+j1+"] : " + PS[i1][j1]);
//			System.out.println("\n\n");
			sb.append(PS[i2][j2] - (PS[i2][j1-1] + PS[i1-1][j2] - PS[i1-1][j1-1])).append("\n");
		}
		
		System.out.println(sb);
	
	}
}
