import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class B7576 {
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		int[][] a = new int[n][m];
		Queue<int[]> q = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				a[i][j] = sc.nextInt();
				if(a[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}
		while(q.size() != 0) {
			int[] pos = q.poll();
			int y = pos[0];
			int x = pos[1];
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if(ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
				if(a[ny][nx] != 0) continue;
				q.offer(new int[] {ny, nx});
				a[ny][nx] = a[y][x] + 1;
			}
		}
		int ret = -1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(a[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				ret = Math.max(ret, a[i][j]);
			}
		}
		System.out.println(ret - 1);
	}
}
