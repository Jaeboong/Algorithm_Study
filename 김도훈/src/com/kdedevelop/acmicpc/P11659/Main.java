package com.kdedevelop.acmicpc.P11659;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stNM = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stNM.nextToken());
        int M = Integer.parseInt(stNM.nextToken());

        int[] sum = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            int input = Integer.parseInt(st.nextToken());
            for (int j = i ; j < N ; j ++) {
                sum[j+1] += input;
            }
        }

        for (int i = 0 ; i < M ; i ++) {
            StringTokenizer stRange = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stRange.nextToken())-1;
            int end = Integer.parseInt(stRange.nextToken());

            bw.write(sum[end] - sum[start] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}