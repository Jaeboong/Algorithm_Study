package com.kdedevelop.swea.P1251;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public int find(int a) {
        if(parents[a] != a) parents[a] = find(parents[a]);
        return parents[a];
    }

    public boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return false;

        parents[parentA] = parentB;
        return true;
    }

    long result;
    int N;
    int[][] ISLANDs;
    int[] parents;
    double E;
    public void solution() throws IOException {
        int TotalTestCase = Integer.parseInt(br.readLine());
        for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
            result = 0;
            N = Integer.parseInt(br.readLine());
            ISLANDs = new int[N][2];
            parents = new int[N];
            for(int i = 0 ; i < N ; i ++) parents[i] = i;
            StringTokenizer inputX = new StringTokenizer(br.readLine());
            StringTokenizer inputY = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i ++) {
                int x = Integer.parseInt(inputX.nextToken());
                int y = Integer.parseInt(inputY.nextToken());
                ISLANDs[i][0] = x;
                ISLANDs[i][1] = y;
            }
            E = Double.parseDouble(br.readLine());
            List<long[]> edges = new ArrayList<>(N*N);
            for(int i = 0 ; i < N ; i ++) {
                int[] a = ISLANDs[i];
                for(int j = 0 ; j < N ; j ++) {
                    if(i == j) continue;
                    int[] b = ISLANDs[j];

                    int distX = a[0] - b[0];
                    if(distX < 0) distX *= -1;
                    int distY = a[1] - b[1];
                    if(distY < 0) distY *= -1;
                    long distance =  ((long) distX * distX) + ((long) distY * distY);

                    long[] edge = {i, j, distance};
                    edges.add(edge);
                }
            }
            Collections.sort(edges, (e1, e2) -> Long.compare(e1[2], e2[2]));

            int count = 0;
            long cost = 0;
            for(long[] edge : edges) {
                if(count == N - 1) break;

                if(union((int) edge[0], (int) edge[1])) {
//                    System.out.println("NODE 1 : " + edge[0] + " || NODE 2 : " + edge[1] + " || WEIGHT : " + edge[2] );
//                    System.out.println("UNION!");
                    ++ count;
                    cost += edge[2];
                }
            }

//            System.out.println("COST : " + cost);
            result = Math.round((E * cost));
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
