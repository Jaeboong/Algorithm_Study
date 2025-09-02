import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B2178 {
    static int N;
    static int M;
    static int[][] miro;
    static int[][] dist;
    static boolean[][] isVisited;
    static int[] dx = new int[] { -1, 0, 1, 0 };
    static int[] dy = new int[] { 0, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);
        miro = new int[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input2 = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                miro[i][j] = Integer.parseInt(input2[j]);
            }
        }
        isVisited = new boolean[N][M];
        bfs(0, 0, 1);
        System.out.println(dist[N - 1][M - 1]);
    }

    static void bfs(int x, int y, int d) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { x, y, d });
        isVisited[x][y] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int cur_x = node[0];
            int cur_y = node[1];
            int cur_dist = node[2];
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur_x + dx[dir];
                int ny = cur_y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (isVisited[nx][ny] || miro[nx][ny] == 0)
                    continue;
                q.add(new int[] { nx, ny, cur_dist + 1 });
                isVisited[nx][ny] = true;
                dist[nx][ny] = cur_dist + 1;
            }
        }
    }
}
