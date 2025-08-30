import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 가중치가 있다
public class B13549 {
    static int Subin;
    static int donsaeng;
    static boolean[] isVisited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Subin = sc.nextInt();
        donsaeng = sc.nextInt();
        isVisited = new boolean[100001];
        int result = bfs(Subin, 0);
        System.out.println(result);
    }

    static int bfs(int start, int t) {
        Deque<int[]> q = new ArrayDeque<>();
        isVisited[start] = true;

        q.add(new int[] { start, t });
        while (!q.isEmpty()) {
            int[] node = q.pollFirst();
            int current = node[0];
            int current_time = node[1];
            if (current == donsaeng) {
                return current_time;
            }
            int next_plus = current + 1;
            int next_minus = current - 1;
            int next_multiple = current * 2;
            if (next_multiple <= 100000 && !isVisited[next_multiple]) {
                isVisited[next_multiple] = true;
                q.addFirst(new int[] { next_multiple, current_time });
            }
            if (next_plus <= 100000 && !isVisited[next_plus]) {
                isVisited[next_plus] = true;
                q.addLast(new int[] { next_plus, current_time + 1 });
            }
            if (next_minus > -1 && !isVisited[next_minus]) {
                isVisited[next_minus] = true;
                q.addLast(new int[] { next_minus, current_time + 1 });
            }

        }

        return 0;
    }
}
