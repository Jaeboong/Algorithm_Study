import java.io.*;
import java.util.*;

public class b2458 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[][] canReach = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			canReach[from][to] = true;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (canReach[i][k] && canReach[k][j]) {
						canReach[i][j] = true;
					}
				}
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			int count = 0;
			for (int j = 1; j <= N; j++) {
				if(i != j && (canReach[i][j] || canReach[j][i])) {
					count++;
				}
			}
			if(count == N-1) {
				ans++;
			}
		}
		
		System.out.println(ans);

	}
}
