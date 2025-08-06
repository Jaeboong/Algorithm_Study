package com.kdedevelop.acmicpc.P5567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[][] graph = new boolean[N][N];

        for (int i = 0 ; i < M ; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            graph[a][b] = true;
            graph[b][a] = true;
        }

        HashSet<Integer> result = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (graph[0][i]) queue.add(i);
        }
        for (int i : queue) {
            result.add(i);
            for (int j = 0 ; j < N ; j++) {
                if(graph[i][j]) result.add(j);
            }
        }

        result.remove(0);

        System.out.println(result.size());

        br.close();
    }
}