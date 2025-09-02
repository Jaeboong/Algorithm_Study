import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B14502_2 {
    static int N;
    static int M;
    static boolean[][] isVisited;
    static int[][] map;
    static int result;
    static int[] dx = new int[] { -1, 0, 1, 0 };
    static int[] dy = new int[] { 0, -1, 0, 1 };
    static ArrayList<int[]> virus;
    static int Max = Integer.MIN_VALUE;
    static int[][] copymap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    virus.add(new int[] { i, j });
                }
            }
        }
        dfs(0);

        System.out.println(Max);
    }

    static void dfs(int cnt) {
        if (cnt == 3) {
            int sum = 0;
            isVisited = new boolean[N][M];
            copymap = new int[N][M];
            for (int i = 0; i < N; i++) {
                copymap[i] = map[i].clone();
            }
            for (int i = 0; i < virus.size(); i++) {
                int[] location = virus.get(i);
                bfs(location[0], location[1]);
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (copymap[i][j] == 0)
                        sum++;
                }
            }
            if (sum > Max)
                Max = sum;
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(cnt + 1);
                    map[i][j] = 0;
                }

            }
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { x, y });
        isVisited[x][y] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int cur_x = node[0];
            int cur_y = node[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur_x + dx[dir];
                int ny = cur_y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (isVisited[nx][ny] || copymap[nx][ny] == 1)
                    continue;
                q.add(new int[] { nx, ny });
                isVisited[nx][ny] = true;
                copymap[nx][ny] = 2;
            }
        }
    }
}
