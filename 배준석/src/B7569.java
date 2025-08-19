import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B7569 {
    static int N;
    static int M;
    static int H;
    static int[][][] arr;
    static int result = 1;
    static int[] dx = { 1, 0, -1, 0, 0, 0 };
    static int[] dy = { 0, -1, 0, 1, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, -1, 1 };
    static boolean hasZero;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<int[]> q = new LinkedList<>();
        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();
        hasZero = false;
        arr = new int[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    arr[i][j][k] = sc.nextInt(); // 입력하면서 넣으면 훨씬 시간 단축할 수 있다
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (arr[i][j][k] == 1) {
                        q.add(new int[] { i, j, k });
                    }
                    if (arr[i][j][k] == 0) {
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
                if (arr[nz][nx][ny] == 0) {
                    arr[nz][nx][ny] = arr[cur_z][cur_x][cur_y] + 1;
                    q.add(new int[] { nz, nx, ny });
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (arr[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    if (arr[i][j][k] > result)
                        result = arr[i][j][k];
                }
            }
        }
        System.out.println(result - 1);
    }
}