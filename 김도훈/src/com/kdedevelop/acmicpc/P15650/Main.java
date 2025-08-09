package com.kdedevelop.acmicpc.P15650;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedWriter bw;
    public static void func(int n, int r, int start, int depth, int[] data) throws IOException {
        if (depth == r) {
            for (int i : data) {
                bw.write((i+1) + " ");
            }
            bw.write("\n");
        } else {
            for (int i = start ; i < n ; i ++) {
                data[depth] = i;
                func(n, r, i+1, depth+1, data);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        func(N, M, 0, 0, new int[M]);

        bw.flush();
        bw.close();
        br.close();
    }
}