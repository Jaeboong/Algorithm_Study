import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B1697 {
    static int Subin;
    static int sister;
    static boolean[] isVisited;
    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        isVisited = new boolean[100001];
        Subin = sc.nextInt();
        sister = sc.nextInt();
        bfs(Subin);
        System.out.println(result);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
            int n_minus = node - 1;
            int n_plus = node + 1;
            int n_multiple = node * 2;
            if (n_minus >= 0 && !isVisited[n_minus]) {
                q.add(n_minus);
                isVisited[n_minus] = true;
            }
            if (n_plus <= 100000 && !isVisited[n_plus]) {
                q.add(n_plus);
                isVisited[n_plus] = true;
            }
            if (n_multiple <= 100000 && !isVisited[n_multiple]) {
                q.add(n_multiple);
                isVisited[n_multiple] = true;
            }
            if (n_minus == sister || n_plus == sister || n_multiple == sister)
                break;
            result++;
        }
    }
}
