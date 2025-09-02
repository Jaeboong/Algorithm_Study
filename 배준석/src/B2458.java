import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2458 {
    static int N;
    static int E;
    static ArrayList<Integer>[] biggerGraph;
    static ArrayList<Integer>[] smallerGraph;
    static boolean[] isVisited;
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        E = sc.nextInt();
        biggerGraph = new ArrayList[N + 1];
        smallerGraph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            biggerGraph[i] = new ArrayList<>();
            smallerGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            addGraph(x, y);
        }
        for (int i = 1; i <= N; i++) {
            int bigger = bfs(i, biggerGraph);
            int smaller = bfs(i, smallerGraph);
            // System.out.println("bigger: " + bigger + " smaller: " + smaller);
            if (bigger + smaller == N + 1) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void addGraph(int x, int y) {
        biggerGraph[x].add(y);
        smallerGraph[y].add(x);
    }

    static int bfs(int x, ArrayList<Integer>[] graph) {
        Queue<Integer> q = new LinkedList<>();
        isVisited = new boolean[N + 1];
        int count = 0;
        q.add(x);
        isVisited[x] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;
            for (int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                if (isVisited[next])
                    continue;
                q.add(next);
                isVisited[next] = true;
            }
        }
        return count;
    }
}
