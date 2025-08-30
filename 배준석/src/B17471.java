import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B17471 {
    static int N;
    static int[] population;
    static ArrayList<Integer>[] graph;
    static boolean[] flag;
    static int min = Integer.MAX_VALUE;
    static boolean[] isVisited;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        population = new int[N + 1];
        flag = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            population[i] = sc.nextInt();
        }
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            int M = sc.nextInt();
            for (int j = 0; j < M; j++) {
                int x = sc.nextInt();
                addGraph(i, x);
            }
        }
        function(0);
        if (count == 0) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }

    }

    static void function(int cnt) {
        if (cnt == N) {
            isVisited = new boolean[N + 1];
            int result = 0;
            boolean isOk = true;
            int idx1 = 0;
            int idx2 = 0;
            for (int i = 1; i <= N; i++) {
                if (flag[i] == true) {
                    idx1 = i;
                } else {
                    idx2 = i;
                }
            }
            bfs(idx1);
            bfs(idx2);
            for (int i = 1; i <= N; i++) {
                if (isVisited[i] == false) {
                    isOk = false;
                    return;
                }
            }
            int flag1Sum = 0;
            int flag2Sum = 0;
            if (isOk) {
                count++;
                for (int i = 1; i <= N; i++) {
                    if (flag[i] == true) {
                        flag1Sum += population[i];
                    } else {
                        flag2Sum += population[i];
                    }
                }
                result = Math.abs(flag1Sum - flag2Sum);
                if (result < min) {
                    min = result;
                }
            }
            return;
        }

        flag[cnt + 1] = true;
        function(cnt + 1);
        flag[cnt + 1] = false;
        function(cnt + 1);
    }

    static void addGraph(int x, int y) {
        graph[x].add(y);
        graph[y].add(x);
    }

    static void bfs(int start) {

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                if (isVisited[next] || flag[node] != flag[next])
                    continue;
                q.add(next);
                isVisited[next] = true;
            }
        }
    }
}
