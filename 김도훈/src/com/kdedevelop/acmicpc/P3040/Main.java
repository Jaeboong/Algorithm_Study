package com.kdedevelop.acmicpc.P3040;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void func(int n, int r, int[] input, int start, int depth, int[] data) {
        if (depth == r) {
            int sum = 0;
            for (int i = 0 ; i < r ; i ++) {
                sum += input[data[i]];
            }
            if (sum == 100) {
                for (int i = 0 ; i < r ; i ++) {
                    System.out.println(input[data[i]]);
                }
            }
        } else {
            for (int i = start ; i < n ; i ++) {
                data[depth] = i;
                func(n, r, input, i+1, depth+1, data);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = new int[9];
        for (int i = 0 ; i < 9 ; i ++) input[i] = Integer.parseInt(br.readLine());

        func(9, 7, input, 0, 0, new int[7]);

        br.close();
    }
}
