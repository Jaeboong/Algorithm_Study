package com.kdedevelop.acmicpc.P17471;

import java.io.*;
import java.util.*;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void dfs(int depth, boolean[] used) {
        if (depth == N) {
            //System.out.println("==========");
            Set<Integer> group1 = new HashSet<>();
            Set<Integer> group2 = new HashSet<>();
            for (int i = 0 ; i < N ; i ++) {
                if (used[i]) group1.add(i);
                else         group2.add(i);
            }
//            System.out.println(Arrays.toString(used));
//            System.out.println(Arrays.toString(group1.toArray()));
//            System.out.println(Arrays.toString(group2.toArray()));
            if (group1.size() == 0 || group2.size() == 0) {
//                System.out.println("ONE OF THE GROUP IS EMPTY");
                return;
            }
            boolean[] visited = new boolean[N];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(group1.iterator().next());
            int group1Population = 0;
            while (true) {
                if (queue.isEmpty()) break;
                int num = queue.poll();
                if (group1.contains(num)) group1Population += population[num];
                group1.remove(num);
                visited[num] = true;
                for (int next : graph[num]) {
                    if (visited[next]) continue;
                    if (!group1.contains(next)) continue;
                    queue.add(next);
                }
            }
            queue.add(group2.iterator().next());
            int group2Population = 0;
            while (true) {
                if (queue.isEmpty()) break;
                int num = queue.poll();
                if (group2.contains(num)) group2Population += population[num];
                group2.remove(num);
                visited[num] = true;
                for (int next : graph[num]) {
                    if (visited[next]) continue;
                    if (!group2.contains(next)) continue;
                    queue.add(next);
                }
            }

            if (group1.size() != 0 || group2.size() != 0) {
//                System.out.println(group1.size());
//                System.out.println(group2.size());
//                System.out.println("ONE OF THE GROUP IS NOT CONNECTED GRAPH");
                return;
            }
            int populationDiff = group1Population - group2Population;
            if (populationDiff < 0) populationDiff *= -1;
//            System.out.println("1 POP : " + group1Population + " || 2 POP : " + group2Population + " || DIFF POP " +  populationDiff);
            result = Math.min(populationDiff, result);
        } else {
            used[depth] = true;
            dfs(depth + 1, used);
            used[depth] = false;
            dfs(depth + 1, used);
        }
    }

    int N;
    int[] population;
    Set<Integer>[] graph;
    int result = Integer.MAX_VALUE;
    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        population = new int[N];
        graph = new HashSet[N];
        StringTokenizer inputPopulation = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            graph[i] = new HashSet<>();
            population[i] = Integer.parseInt(inputPopulation.nextToken());
        }
        for (int i = 0 ; i < N ; i ++) {
            StringTokenizer lineInput = new StringTokenizer(br.readLine());
            int nearCount = Integer.parseInt(lineInput.nextToken());
            for (int j = 0 ; j < nearCount ; j++) {
                int near = Integer.parseInt(lineInput.nextToken())-1;
                graph[i].add(near);
                graph[near].add(i);
            }
        }

        dfs(0, new boolean[N]);

        bw.write(result == Integer.MAX_VALUE ? String.valueOf(-1) : String.valueOf(result));

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
