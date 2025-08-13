package 구간합;

import java.util.*;
import java.io.*;

public class b11659 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] prefixSum = new int[n];
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (i == 0)
				prefixSum[i] += num;
			else
				prefixSum[i] = prefixSum[i - 1] + num;
		}

		for (int a = 0; a < m; a++) {
			st = new StringTokenizer(br.readLine());

			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			if (i > 0)
				sb.append(prefixSum[j] - prefixSum[i - 1]).append("\n");
			else if (i == 0)
				sb.append(prefixSum[j]).append("\n");

		}
		System.out.println(sb);
	}

}
