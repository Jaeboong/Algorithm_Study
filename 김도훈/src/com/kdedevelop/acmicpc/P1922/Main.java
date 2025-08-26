package com.kdedevelop.acmicpc.P1922;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public int find(int a) {
        if(parents[a] != a) parents[a] = find(parents[a]);
        return parents[a];
    }

    public boolean union(int a, int b) {
        int parentsA = find(a);
        int parentsB = find(b);

        if(parentsA == parentsB) return false;

        parents[parentsA] = parentsB;
        return true;
    }

    int N;
    int M;
    int[][] EDGEs;
    int[] parents;
    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        EDGEs = new int[M][];
        parents = new int[N];
        for(int i = 0 ; i < N ; i ++) parents[i] = i;
        for(int i = 0 ; i < M ; i ++) {
            StringTokenizer inputLine = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(inputLine.nextToken()) - 1;
            int b = Integer.parseInt(inputLine.nextToken()) - 1;
            int weight = Integer.parseInt(inputLine.nextToken());
            int[] edge = {a, b, weight};
            EDGEs[i] = edge;
        }
        Arrays.sort(EDGEs, (e1, e2) -> Integer.compare(e1[2], e2[2]));

        int count = 0;
        int result = 0;

        for(int[] edge : EDGEs) {
            if(count == N - 1) break;

            int a = edge[0];
            int b = edge[1];
            int weight = edge[2];

            if(union(a, b)) {
                ++ count;
                result += weight;
            }
        }

        bw.write(String.valueOf(result));

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}