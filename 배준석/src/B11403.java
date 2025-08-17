import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class B11403 {
    static int N;
    static int[][] arr;
    static ArrayList<Integer>[] graph;
    static int[][] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        result = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    addGraph(i, j);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            bfs(i);
        }
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
    }

    static void bfs(int start) {
        boolean[] isVisited = new boolean[N];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                if (isVisited[next])
                    continue;
                q.add(next);
                isVisited[next] = true;
            }
        }
        for (int i = 0; i < N; i++) {
            if (isVisited[i]) {
                System.out.printf("%d ", 1);
            } else {
                System.out.printf("%d ", 0);
            }
        }
        System.out.println();
    }
}
