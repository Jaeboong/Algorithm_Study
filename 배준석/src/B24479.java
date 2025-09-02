import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B24479 {
    static int N;
    static int E;
    static int start;
    static boolean[] isVisited;
    static ArrayList<Integer>[] graph;
    // static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer> result;
    static int[] orders;
    static int order;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        E = sc.nextInt();
        start = sc.nextInt();
        graph = new ArrayList[N + 1];
        isVisited = new boolean[N + 1];
        orders = new int[N + 1];

        result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            addGraph(x, y);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }
        dfs(start);
        for (int i = 1; i <= N; i++) {
            System.out.println(orders[i]);
        }
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
        graph[y].add(x);
    }

    static void dfs(int start) {
        isVisited[start] = true;
        order++;
        result.add(start);
        orders[start] = order;
        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (isVisited[next])
                continue;
            dfs(next);
        }
    }
}
