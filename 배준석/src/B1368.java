import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B1368 {
    static int[] cost; // 자체적으로 우물을 파는 비용을 저장
    static int N; // 노드 개수 입력
    static int[][] map; // 물을 끌어오는 비용을 위한 인접 행렬
    static int[] parents; // union find를 위한 parents 배열
    static int min = Integer.MAX_VALUE;
    static int result;
    static ArrayList<Edge> edgelist;
    static int E;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cost = new int[N + 1];
        map = new int[N + 1][N + 1];
        parents = new int[N + 1];
        edgelist = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            cost[i] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] != 0) {
                    edgelist.add(new Edge(i, j, map[i][j]));
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            edgelist.add(new Edge(0, i, cost[i]));
        }
        make();
        Collections.sort(edgelist);

        for (int i = 0; i < edgelist.size(); i++) {
            Edge e = edgelist.get(i);
            if (union(e.from, e.to)) {
                result += e.weight;
                E++;
            }
            if (E == N)
                break;
        }

        System.out.println(result);
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static void make() {
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (parents[a] == a)
            return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot)
            return false;
        parents[aRoot] = bRoot;
        return true;
    }
}
