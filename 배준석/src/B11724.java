import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class B11724 {
    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static boolean[] isVisited;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        graph = new ArrayList[N + 1];
        isVisited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            addGraph(x, y);
        }
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        System.out.println(count);
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
        graph[y].add(x);
    }

    static void bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        if (isVisited[start]) {
            return;
        }
        q.add(start);
        isVisited[start] = true;
        count++;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                if (isVisited[next])
                    continue;
                isVisited[next] = true;
                q.add(next);
            }
        }
    }

    static void dfs(int start) {
        isVisited[start] = true;

        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (isVisited[next])
                continue;
            dfs(next);
        }
    }
}
