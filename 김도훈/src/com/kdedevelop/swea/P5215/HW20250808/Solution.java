package com.kdedevelop.swea.P5215.HW20250808;

import java.io.*;
import java.util.StringTokenizer;

class Food {
    public Food(String input) {
        StringTokenizer st = new StringTokenizer(input);
        this.tasty = Integer.parseInt(st.nextToken());
        this.cal = Integer.parseInt(st.nextToken());
    }
    int tasty;
    int cal;
}
public class Solution {

    static int result;
    static void dfs(Food[] foods, int N, int L, int depth, boolean[] used) {
        if (depth == N) {
            int cal = 0;
            int tasty = 0;
            for (int i = 0 ; i < N ; i ++) {
                if (used[i]) {
                    cal += foods[i].cal;
                    tasty += foods[i].tasty;
                }
                if (cal > L) return;
            }
            result = Math.max(result, tasty);
        } else {
            used[depth] = true;
            dfs(foods, N, L, depth+1, used);
            used[depth] = false;
            dfs(foods, N, L, depth+1, used);
        }
    }
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("/Users/kdh/Downloads/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TotalTestCase = Integer.parseInt(br.readLine());

        for (int testCase = 0 ; testCase < TotalTestCase ; testCase++) {
            result = 0;
            StringTokenizer inputNL = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(inputNL.nextToken());
            int L = Integer.parseInt(inputNL.nextToken());

            Food[] foods = new Food[N];
            for (int i = 0 ; i < N ; i++) {
                foods[i] = new Food(br.readLine());
            }

            dfs(foods, N, L, 0, new boolean[N]);

            bw.write("#" + (testCase+1) + " " + result + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
