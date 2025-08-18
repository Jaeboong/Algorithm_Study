import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B1697 {
    static int N;
    static int M;
    static boolean[] isVisited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        isVisited = new boolean[100001];
        N = sc.nextInt();
        M = sc.nextInt();
        int time = bfs(N, 0);
        System.out.println(time);
    }

    static int bfs(int start, int time) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { start, time });
        isVisited[start] = true;

        int result = 0;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int cur_x = node[0];
            int cur_time = node[1];
            if (cur_x == M) {
                result = node[1];
                break;
            }
            int nx_minus = cur_x - 1;
            int nx_plus = cur_x + 1;
            int nx_multiple = cur_x * 2;
            if (nx_minus > -1 && !isVisited[nx_minus]) {
                q.add(new int[] { nx_minus, cur_time + 1 });
                isVisited[nx_minus] = true;
            }
            if (nx_plus < 100001 && !isVisited[nx_plus]) {
                q.add(new int[] { nx_plus, cur_time + 1 });
                isVisited[nx_plus] = true;
            }
            if (nx_multiple < 100001 && !isVisited[nx_multiple]) {
                q.add(new int[] { nx_multiple, cur_time + 1 });
                isVisited[nx_multiple] = true;
            }

        }
        return result;
    }
}
