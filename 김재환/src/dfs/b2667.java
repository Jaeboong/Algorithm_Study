package dfs;

import java.io.*;
import java.util.*;

public class b2667 {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static List<Integer> ans= new ArrayList<>();
	static int count = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		String line;
		for (int i = 0; i < N; i++) {
			line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
			}
		}

//		System.out.println(Arrays.deepToString(map));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					ans.add(dfs(i, j, 1));
					count++;
//					System.out.println();
//					for (int a = 0; a < N; a++) {
//						for (int b = 0; b < N; b++) {
//							if (visited[a][b])
//								System.out.print("1 ");
//							else
//								System.out.print("0 ");
//						}
//						System.out.println();
//					}
				}
			}
		}
		sb.append(count).append("\n");
		Collections.sort(ans);
		//for문 안에는 정적인 값이라면 매서드 호출하지 않고 따로 변수를 선언해서 쓰거나 for : 를 쓰기
		for(int result : ans) {
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static int dfs(int r, int c, int cnt) {
		visited[r][c] = true;
		int[] dr = { 1, -1, 0, 0 };
		int[] dc = { 0, 0, 1, -1 };

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] && map[nr][nc] == 1) {
				cnt = dfs(nr, nc, cnt+1);
			} 
		}
		return cnt;
	}

}
