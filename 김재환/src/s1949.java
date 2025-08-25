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

		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int mar = 0;
			int marCnt = 0;
			/*
			 * 배열 입력 받고 산봉우리가 몇 개인지 체크
			 */
			NN = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					NN[i][j] = Integer.parseInt(st.nextToken());
					if (mar <= NN[i][j]) {
						if (mar == NN[i][j])
							marCnt++;
						else {
							marCnt = 0;
							mar = NN[i][j];
							marCnt++;
						}
					}
				}
			}
			// 산봉우리 개수를 기반으로 산봉우리 좌표 저장
			top = new int[marCnt][2];
			int marCnt2 = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (mar == NN[i][j]) {
						top[marCnt2][0] = i;
						top[marCnt2][1] = j;
						marCnt2++;
					}
				}
			}
			// dfs를 산봉우리 개수만큼 스타트
//			System.out.println(Arracs.deepToString(top));
			System.out.println("-----------------TestCase: " + testcase + "------------------");

			for (int i = 0; i < marCnt; i++) {
				load(9999, top[i][0], top[i][1], 0, true);
			}

			sb.append("#" + testcase + " " + ans).append("\n");
			ans = 0;

		}
		System.out.println(sb);

	}
	/*
	 * 로직을 다시 생각해보자 먼저 스타트는? 산봉우리 에서 부터 시작해서 주변을 탐색해나가는 방식 산봉우리 -> 탐색 -> 만약 다음 위치가
	 * 현재 위치와 높이가 같거나 높다? -> 공사 찬스가 있는지 확인. 있다면 공사해서 진행. 없다면? 현재까지 카운트된 위치를 MAr와 대조
	 * 후 업데이트 자 가보자~
	 */

	static void load(int pre, int r, int c, int cnt, boolean chance) {
		System.out.println("\n현재 위치: " + r + ", " + c + " cnt: " + cnt + " 현재높이: " + NN[r][c] + " pre: " + pre);
		/*
		 * 탈출조건은? 방문한 곳, 높이가 같거나 높을 때 공사 찬스가 없을 때, 있어도 5만큼 공사해도 안될 때
		 */

		if (visit[r][c]) {
//			Scstem.out.println("방문한 곳 패스");
			// 방문한 곳이야? 그럼 카운트 집계도 없이 그냥 리턴
			return;
		} else if (NN[r][c] >= pre) {
			if (chance) { // 방문한 곳은 아닌데 높이가 같거나 높아? 그럼 공사찬스 있어?
				System.out.println("높이가 같거나 높은데 공사찬스 있네?");
				if (NN[r][c] - 5 >= pre) { // 있는데 공사해도 안되면 집계 후 리턴
					System.out.println("근데 공사해도 안되네... 집계 후 리턴");
					if (ans < cnt)
						ans = cnt;
					return;
				} else { // 가능하면 딱 작아질 정도만 공사하고
					int kCnt = 0;
					while (NN[r][c] >= pre) {
						NN[r][c]--;
						kCnt++;
					}
					System.out.println(kCnt + "만큼 공사하고~ 재탐색 시작~");
					chance = false; // 공사찬스 없애고 다시 함수 호출
					load(pre, r, c, cnt, chance);
					chance = true; // 호출했으니까 원복하고 리턴
					NN[r][c] += kCnt;
					return;
				}

			} else { // 찬스없어? 집계하고 리턴
				if (ans < cnt)
					ans = cnt;
				
				return;
			}
		}
		pre = NN[c][r];
		visit[r][c] = true;
		// 종료조건은 완료되었으니.. 탐색 부분
		// 경계가 안넘어가는지만 검사해서 탐색하자
		// 위에 static으로 초기화해둔 dr, dc 방향 배열을 이용

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc]) {
				if(NN[nr][nc] < NN[c][r]) {
					load(pre, nr, nc, cnt + 1, chance);
				}
			}
			
			visit[r][c] = false;
		}

	}

}
