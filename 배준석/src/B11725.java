import java.util.ArrayList;
import java.util.Scanner;

public class B11725 {
    static int N;
    static int E;
    static ArrayList<Integer>[] graph;
    static boolean[] isVisited;
    static ArrayList<Point> arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        graph = new ArrayList[N + 1];
        isVisited = new boolean[N + 1];
        arr = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            addGraph(x, y);
        }
        dfs(1);
        arr.sort((a, b) -> a.child - b.child);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).parent);
        }
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
        graph[y].add(x);
    }

    static void dfs(int start) {
        isVisited[start] = true;

        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (isVisited[next])
                continue;
            arr.add(new Point(start, next));
            dfs(next);
        }
    }

    static class Point {
        int parent;
        int child;

        Point(int parent, int child) {
            this.parent = parent;
            this.child = child;
        }

    }
}
