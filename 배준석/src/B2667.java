import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class B2667 {
    static int N; // 사이즈
    static int[][] arr; // 입력을 위한 배열
    static boolean[][] isVisited;
    static int count = 0;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    static ArrayList<Integer> arr2;
    static int numberBuilding;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        isVisited = new boolean[N][N];
        arr2 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    numberBuilding = 0;
                    bfs(i, j);
                    arr2.add(numberBuilding);
                }
            }
        }
        Collections.sort(arr2);
        System.out.println(count);
        for (int i = 0; i < arr2.size(); i++) {
            if (arr2.get(i) != 0)
                System.out.println(arr2.get(i));
        }

    }

    static void bfs(int startX, int startY) {
        Queue<int[]> q = new LinkedList<>();
        if (isVisited[startX][startY]) {
            return;
        }
        q.add(new int[] { startX, startY });
        isVisited[startX][startY] = true;
        count++;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            numberBuilding++;
            for (int dir = 0; dir < 4; dir++) {
                int nx = node[0] + dx[dir];
                int ny = node[1] + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;
                if (isVisited[nx][ny] || arr[nx][ny] != 1)
                    continue;
                q.add(new int[] { nx, ny });
                isVisited[nx][ny] = true;
            }
        }
    }
}
