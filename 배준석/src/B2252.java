import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2252 {
    static ArrayList<Integer>[] graph;
    static int N;
    static int E;
    static int[] inD;
    static Queue<Integer> q = new LinkedList<>();
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        E = sc.nextInt();
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        inD = new int[N + 1];
        for (int i = 0; i < E; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            addGraph(x, y);
        }
        for (int i = 1; i <= N; i++) {
            if (inD[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);
            for (int i = 0; i < graph[node].size(); i++) {
                int to = graph[node].get(i);
                inD[to]--;
                if (inD[to] == 0) {
                    q.add(to);
                }
            }

        }
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%d ", result.get(i));
        }
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
        inD[y]++;
    }
}
