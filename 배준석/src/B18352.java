import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Scanner;

// 문제 잘 읽어볼 것, 오름차순 정렬
public class B18352 {
    static int N; // 노드 갯수
    static int M; // 간선 갯수
    static int K; // 거리 정보
    static int X; // 출발 도시의 번호
    static boolean[] isVisited; // 방문 여부
    static ArrayList<Integer>[] city; // 도시 그래프
    static int count = 0; // 결과를 위한 변수
    static ArrayList<Integer> arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        X = sc.nextInt();
        isVisited = new boolean[N + 1];
        city = new ArrayList[N + 1];
        arr = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            city[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            addReation(x, y);
        }
        bfs(X, 0);
        if (!arr.isEmpty()) {
            Collections.sort(arr);
            for (int i = 0; i < arr.size(); i++) {
                System.out.println(arr.get(i));
            }
        } else {
            System.out.println(-1);
        }
    }

    static void addReation(int x, int y) {
        city[x].add(y);
    }

    static void bfs(int start, int depth) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { start, depth });
        isVisited[start] = true;

        while (!dq.isEmpty()) {
            int[] node = dq.poll();
            if (node[1] == K)
                arr.add(node[0]);

            for (int i = 0; i < city[node[0]].size(); i++) {
                int next = city[node[0]].get(i);
                if (isVisited[next])
                    continue;
                dq.add(new int[] { next, node[1] + 1 });
                isVisited[next] = true;
            }

        }

    }
}
