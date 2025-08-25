package com.kdedevelop.acmicpc.P2839;

import java.io.*;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());

        int kg5 = N / 5;
        int remain;
        int kg3 = 0;
        for( ; kg5 >= 0 ; kg5 --) {
            remain = N - (5 * kg5);
            kg3 = remain / 3;
            if (kg5 * 5 + kg3 * 3 == N) break;
        }

        if (kg5 * 5 + kg3 * 3 == N) bw.write(String.valueOf(kg3 + kg5));
        else bw.write("-1");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
