import java.util.Scanner;

public class Main {
    static int N;
    static int[][] arr;
    static int point;
    static int point_x;
    static int point_y;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        point = sc.nextInt();
        arr = new int[N][N];
        int cur_x = 0;
        int cur_y = 0;
        int dir = 0;
        int square = N * N - 1;
        arr[cur_x][cur_y] = N * N;
        while (true) {

            int nx = cur_x + dx[dir];
            int ny = cur_y + dy[dir];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || arr[nx][ny] != 0) {
                dir = (dir + 1) % 4;
                continue;
            }
            arr[nx][ny] = square--;
            cur_x = nx;
            cur_y = ny;
            if (nx == N / 2 && ny == N / 2)
                break;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == point) {
                    point_x = i + 1;
                    point_y = j + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        sb.append(point_x).append(' ').append(point_y);
        System.out.print(sb);
    }
}
