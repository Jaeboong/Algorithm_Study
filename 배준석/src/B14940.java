import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B14940 {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] isVisited;
    static int[][] distance;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = new int[] { -1, 0, 1, 0 };
    static int[] dy = new int[] { 0, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        map = new int[N][M];
        isVisited = new boolean[N][M];
        distance = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] str2 = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(str2[j]);
            }
        }
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }
        bfs(startX, startY);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    System.out.print("0 ");
                else if (!isVisited[i][j])
                    System.out.print("-1 ");
                else
                    System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bfs(int startX, int startY) {
        Queue<int[]> q = new LinkedList<>();
        int[] node = new int[] { startX, startY };
        q.add(node);
        isVisited[startX][startY] = true;
        distance[startX][startY] = 0;
        while (!q.isEmpty()) {
            int[] c = q.poll();
            int cur_x = c[0];
            int cur_y = c[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur_x + dx[dir];
                int ny = cur_y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (isVisited[nx][ny] || map[nx][ny] == 0)
                    continue;
                q.add(new int[] { nx, ny });
                isVisited[nx][ny] = true;
                distance[nx][ny] = distance[cur_x][cur_y] + 1;
            }
        }
    }
}
