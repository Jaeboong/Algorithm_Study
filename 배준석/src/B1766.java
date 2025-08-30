import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B1766 {
    // 어떤 것을 선행으로 풀 것인지에 대한 문제이기 때문에
    // 위상 정렬 문제이다
    // 쉬운 문제(숫자가 작은 문제)를 먼저 풀어야 하기 때문에 priority que 사용
    static int N;
    static int E;
    static ArrayList<Integer>[] graph; // 그래프
    static int[] inDegree; // 몇 개의 간선이 자신에게 들어오는지 확인
    static ArrayList<Integer> result; // 결과 저장
    static PriorityQueue<Integer> q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        E = sc.nextInt();

        graph = new ArrayList[N + 1];
        inDegree = new int[N + 1];
        result = new ArrayList<>();
        q = new PriorityQueue<>();
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            addGraph(from, to);
        }
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);
            for (int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%d ", result.get(i));
        }
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
        inDegree[y]++;
    }
}
