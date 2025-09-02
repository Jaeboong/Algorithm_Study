import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B7569_2 {
    static int N;
    static int M;
    static int H;
    static int[][][] tomato;
    static int[] dx = new int[] { -1, 0, 1, 0, 0, 0 };
    static int[] dy = new int[] { 0, 1, 0, -1, 0, 0 };
    static int[] dz = new int[] { 0, 0, 0, 0, -1, 1 };
    static boolean[][][] isVisited;
    static int Max = -2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<int[]> q = new LinkedList<>();
        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();
        tomato = new int[H][N][M];
        isVisited = new boolean[H][N][M];
        boolean hasZero = false;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    tomato[i][j][k] = sc.nextInt();
                    if (tomato[i][j][k] == 1) {
                        q.add(new int[] { i, j, k });
                        isVisited[i][j][k] = true;
                    } else if (tomato[i][j][k] == -1) {
                        continue;
                    } else {
                        hasZero = true;
                    }
                }
            }
        }
        if (!hasZero) {
            System.out.println(0);
            return;
        }
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int cur_z = node[0];
            int cur_x = node[1];
            int cur_y = node[2];
            for (int dir = 0; dir < 6; dir++) {
                int nz = cur_z + dz[dir];
                int nx = cur_x + dx[dir];
                int ny = cur_y + dy[dir];
                if (nz < 0 || nz >= H || nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (isVisited[nz][nx][ny] || tomato[nz][nx][ny] == -1)
                    continue;
                tomato[nz][nx][ny] = tomato[cur_z][cur_x][cur_y] + 1;
                isVisited[nz][nx][ny] = true;
                q.add(new int[] { nz, nx, ny });
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tomato[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    } else {
                        if (tomato[i][j][k] > Max)
                            Max = tomato[i][j][k];
                    }
                }
            }
        }
        System.out.println(Max - 1);
    }
}
