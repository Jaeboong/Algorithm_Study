import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B14502 {
    /* 벽을 세울 때는 bfs를 돌려서 모든 경우를 탐색한다 */
    static int N;
    static int M;
    static int[][] map;
    static int count;
    static int Max = 0;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }
        dfs(0);
        System.out.println(Max);
    }

    static void dfs(int cnt) {
        if (cnt == 3) { // 벽을 3개 세우면 바이러스 퍼지게 하고 count 세기
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 벽 세우기
                    dfs(cnt + 1);
                    map[i][j] = 0; // 원상태로 복귀
                }
            }
        }
    }

    static void bfs() {
        count = 0;
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 2) {
                    q.add(new int[] { i, j });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] node = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = node[0] + dx[dir];
                int ny = node[1] + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (copy[nx][ny] != 0)
                    continue;
                q.add(new int[] { nx, ny });
                copy[nx][ny] = 2;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) {
                    count++;
                }
            }
        }
        if (count > Max) {
            Max = count;
        }
    }
}
