import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2468 {
    static int Max = 1;
    static int N;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = new int[] { -1, 0, 1, 0 };
    static int[] dy = new int[] { 0, 1, 0, -1 };
    static int count;
    static int m = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] > m) {
                    m = map[i][j];
                }
            }
        }
        int idx = 1;
        while (true) {
            count = 0;
            isVisited = new boolean[N][N];
            if (idx > m)
                break;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= idx) {
                        isVisited[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!isVisited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            idx++;
            if (count > Max) {
                Max = count;
            }
        }
        System.out.println(Max);
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { x, y });
        isVisited[x][y] = true;
        count++;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int cur_x = node[0];
            int cur_y = node[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur_x + dx[dir];
                int ny = cur_y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;
                if (isVisited[nx][ny])
                    continue;
                q.add(new int[] { nx, ny });
                isVisited[nx][ny] = true;
            }
        }
    }
}
