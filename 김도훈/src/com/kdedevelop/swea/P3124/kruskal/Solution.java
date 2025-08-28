package com.kdedevelop.swea.P3124.kruskal;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public int find(int a) {
        if(parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    public boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return false;

        parent[parentA] = parentB;
        return true;
    }

    int V;
    int E;
    int[][] EDGEs;
    int[] parent;
    public void solution() throws IOException {
        int TotalTestCase = Integer.parseInt(br.readLine());
        for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
            StringTokenizer inputVE = new StringTokenizer(br.readLine());
            V = Integer.parseInt(inputVE.nextToken());
            E = Integer.parseInt(inputVE.nextToken());
            parent = new int[V];
            for(int i = 0 ; i < V ; i ++) parent[i] = i;
            EDGEs = new int[E][];
            for(int i = 0 ; i < E ; i ++) {
                StringTokenizer inputABC = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(inputABC.nextToken())-1;
                int B = Integer.parseInt(inputABC.nextToken())-1;
                int C = Integer.parseInt(inputABC.nextToken());
                int[] edge = {A, B, C};
                EDGEs[i] = edge;
            }
            Arrays.sort(EDGEs, (e1, e2) -> Integer.compare(e1[2], e2[2]));

            int count = 0;
            long result = 0;

            for(int[] edge : EDGEs) {
                if(count == V - 1) break;

                int a = edge[0];
                int b = edge[1];
                int weight = edge[2];

                if(union(a, b)) {
                    ++ count;
                    result += weight;
                }
            }

            bw.write("#" + (testCase + 1) + " " + result + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }
}
