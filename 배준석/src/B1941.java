import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class B1941 {
    static char[][] students;
    static boolean[][] isSelected;
    static int[] dx = new int[] { -1, 0, 1, 0 };
    static int[] dy = new int[] { 0, -1, 0, 1 };
    static int ans;
    static int[][] Select;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        students = new char[5][5];
        Select = new int[5][5];
        for (int i = 0; i < 5; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 5; j++) {
                students[i][j] = input[j].charAt(0);
            }
        }
        select(0, 0);
        System.out.println(ans);
    }

    static void select(int start, int cnt) {
        if (cnt == 7) {
            int S = 0;
            ArrayList<int[]> result = new ArrayList<>();
            int connected = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Select[i][j] == 1) {
                        result.add(new int[] { i, j });
                        if (students[i][j] == 'S') {
                            S++;
                        }
                    }
                }
            }
            isSelected = new boolean[5][5];
            if (S >= 4) {
                connected = bfs(result.get(0)[0], result.get(0)[1]);
            } else {
                return;
            }
            if (connected == 7) {
                ans++;
            }
            return;
        }

        for (int idx = start; idx < 25; idx++) {
            int x = idx / 5;
            int y = idx % 5;

            if (Select[x][y] == 1)
                continue;

            Select[x][y] = 1;
            select(idx + 1, cnt + 1);
            Select[x][y] = 0;
        }
    }

    static int bfs(int x, int y) {
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { x, y });
        isSelected[x][y] = true;
        count++;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int cur_x = node[0];
            int cur_y = node[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur_x + dx[dir];
                int ny = cur_y + dy[dir];
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
                    continue;
                if (isSelected[nx][ny] || Select[nx][ny] == 0)
                    continue;
                q.add(new int[] { nx, ny });
                isSelected[nx][ny] = true;
                count++;
            }
        }
        return count;
    }
}
