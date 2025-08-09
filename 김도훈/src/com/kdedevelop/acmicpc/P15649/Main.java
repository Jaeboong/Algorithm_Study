package com.kdedevelop.acmicpc.P15649;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    static BufferedWriter bufferedWriter;
    public static void main(String[] args) throws IOException {
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        nCr(N, M, 0, new int[M], new boolean[N]);

        bufferedWriter.flush();
        sc.close();
    }

    public static void nCr(int n, int r, int depth, int[] data, boolean[] visited) throws IOException {
        if (depth == r) {
            for (int i = 0; i < r; i++) {
                bufferedWriter.write((data[i]+1) + " ");
            }
            bufferedWriter.write("\n");
        } else {
            for (int i = 0 ; i < n ; i ++) {
                if (visited[i]) continue;
                visited[i] = true;
                data[depth] = i;
                nCr(n, r, depth + 1, data, visited);
                visited[i] = false;
            }
        }
    }
}