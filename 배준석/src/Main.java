import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static boolean[] isVisited;
    static int start;
    static StringBuilder sb = new StringBuilder();
    static StringBuilder sb2 = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        start = Integer.parseInt(str[2]);
        graph = new ArrayList[N + 1];
        isVisited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            String[] str2 = br.readLine().split(" ");
            addGraph(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]));
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }
        dfs(start);
        for (int i = 1; i <= N; i++) {
            isVisited[i] = false;
        }
        bfs(start);
        System.out.println(sb);
        System.out.println(sb2);
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
        graph[y].add(x);
    }

    static void dfs(int start) {
        isVisited[start] = true;
        sb.append(start).append(" ");
        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (isVisited[next])
                continue;
            dfs(next);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {

            int node = q.poll();
            sb2.append(node).append(" ");
            for (int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                if (isVisited[next])
                    continue;
                q.add(next);
                isVisited[next] = true;
            }
        }
    }
}
