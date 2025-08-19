import java.util.*;
import java.io.*;

public class s1949 {

	static int[][] top;
	static int[][] NN;
	static boolean[][] visit;
	static int N;
	static int K;
	static int ans = 0;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testcase = 0; testcase < T; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int max = 0;
			int maxCnt = 0;

			NN = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					NN[i][j] = Integer.parseInt(st.nextToken());
					if (max <= NN[i][j]) {
						if (max == NN[i][j])
							maxCnt++;
						else {
							maxCnt = 0;
							max = NN[i][j];
							maxCnt++;
						}
					}
				}
			}

			top = new int[maxCnt][2];
			int maxCnt2 = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max == NN[i][j]) {
						top[maxCnt2][0] = i;
						top[maxCnt2][1] = j;
						maxCnt2++;
					}
				}
			}

			System.out.println(Arrays.deepToString(top));
			
			for(int i=0; i<maxCnt; i++) {
				load(9999, top[i][0], top[i][1], 1, false);
			}
			
			System.out.println(ans);

		}

	}

	static void load(int pre, int x, int y, int cnt, boolean chance) {
		System.out.println(cnt);
		
		if (NN[x][y] >= pre) {
			if (chance) {
				if (ans < cnt) {
					ans = cnt;
					System.out.println("정답 갱신: " + ans);
					return;
				}
			} else {
				chance = true;
				load(pre, x, y, cnt+1, chance);
				chance = false;
				System.out.println("use chance");
			}		
		}
		
		if(visit[x][y]) return;
		
		pre = NN[x][y];

		for (int i = 0; i < 4; i++) {
			if (0 <= x + dr[i] && x + dr[i] < N) {
				x += dr[i];
			}
			if (0 <= y + dc[i] && y + dc[i] < N) {
				y += dc[i];
			}
			visit[x][y] = true;
			
			load(pre, x, y, cnt+1, chance);
			
			visit[x][y] = false;
			
			if (0 <= x + dr[i] && x + dr[i] < N) {
				x -= dr[i];
			}
			if (0 <= y + dc[i] && y + dc[i] < N) {
				y -= dc[i];
			}
		}

	}

}
