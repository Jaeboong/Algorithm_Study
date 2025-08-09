import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2606 {
    static ArrayList<Integer>[] graph;
    static boolean[] isVisited;
    static int N;
    static int M;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new ArrayList[N + 1];
        isVisited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            addGraph(x, y);
        }
        bfs(1);
        System.out.println(count);
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
        graph[y].add(x);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;
        while (!q.isEmpty()) {
            int node = q.poll();

            for (int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                if (isVisited[next])
                    continue;
                q.add(next);
                isVisited[next] = true;
                count++;
            }
        }

    }
}
