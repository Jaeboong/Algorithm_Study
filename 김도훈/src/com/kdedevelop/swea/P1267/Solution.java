package com.kdedevelop.swea.P1267;

import java.io.*;
import java.util.*;

public class Solution {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int V;
    int E;
    List<Integer>[] graph;
    int[] degrees;
    public void solution() throws IOException {
        for (int testCase = 0 ; testCase < 10 ; testCase++) {
            StringTokenizer inputVE = new StringTokenizer(br.readLine());
            V = Integer.parseInt(inputVE.nextToken());
            E = Integer.parseInt(inputVE.nextToken());
            graph = new List[V];
            degrees = new int[V];
            for (int i = 0 ; i < V ; i ++) graph[i] =  new ArrayList<>();
            StringTokenizer inputLine = new StringTokenizer(br.readLine());
            for (int i = 0 ; i < E ; i ++) {
                int from = Integer.parseInt(inputLine.nextToken())-1;
                int to = Integer.parseInt(inputLine.nextToken())-1;
                graph[from].add(to);
                ++ degrees[to];
            }

            List<Integer> result = new LinkedList<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0 ; i < V ; i ++) if (degrees[i] == 0) queue.offer(i);

            while (true) {
                if (queue.isEmpty()) break;

                int curr = queue.poll();
                result.add(curr);

                for (int next : graph[curr]) {
                    if (--degrees[next] == 0) queue.offer(next);
                }
            }

            bw.write("#" + (testCase + 1) + " ");
            for (int temp : result) {
                bw.write((temp + 1) + " ");
            }
            bw.write("\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }
}
