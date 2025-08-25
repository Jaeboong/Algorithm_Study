package com.kdedevelop.acmicpc.P1920;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public boolean function(int start, int end, int target) {
        if (end < start) return false;
        int middle = (end + start) / 2;
        if (target == INPUT[middle]) {
            return true;
        } else if (target < INPUT[middle]) {
            return function(start, middle - 1, target);
        } else if (target > INPUT[middle]) {
            return function(middle + 1, end, target);
        }
        return false;
    }

    int N;
    int[] INPUT;
    int M;
    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        INPUT = new int[N];
        StringTokenizer inputNumbers = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; i ++) {
            INPUT[i] = Integer.parseInt(inputNumbers.nextToken());
        }
        Arrays.sort(INPUT);

        M = Integer.parseInt(br.readLine());
        StringTokenizer inputMs = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < M ; i ++) {
            int number = Integer.parseInt(inputMs.nextToken());
            bw.write((function(0, N-1, number) ? "1" : "0") + "\n");
        }



        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
