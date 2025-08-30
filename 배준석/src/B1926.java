import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B1926 {
    static int N;
    static int M;
    static int[][] picture;
    static boolean[][] isVisited;
    static int[] dx = new int[] { -1, 0, 1, 0 };
    static int[] dy = new int[] { 0, -1, 0, 1 };
    static int count = 0;
    static ArrayList<Integer> width;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        picture = new int[N][M];
        isVisited = new boolean[N][M];
        width = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                picture[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isVisited[i][j] && picture[i][j] == 1) {
                    count++;
                    bfs(i, j);
                }
            }
        }
        Collections.sort(width);
        System.out.println(count);
        if (count == 0) {
            System.out.println(0);
        } else {
            System.out.println(width.get(width.size() - 1));
        }

    }

    static void bfs(int x, int y) {
        int w = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { x, y });
        isVisited[x][y] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            w++;
            int cur_x = node[0];
            int cur_y = node[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur_x + dx[dir];
                int ny = cur_y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (isVisited[nx][ny] || picture[nx][ny] == 0)
                    continue;
                q.add(new int[] { nx, ny });
                isVisited[nx][ny] = true;
            }
        }
        width.add(w);
    }
}
