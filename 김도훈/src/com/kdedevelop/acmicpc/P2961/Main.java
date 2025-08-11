package com.kdedevelop.acmicpc.P2961;

import java.io.*;
import java.util.StringTokenizer;

class Food {
    public Food(int a, int b) {
        this.flavorA = a;
        this.flavorB = b;
    }
    public Food(String input) {
        StringTokenizer st = new StringTokenizer(input);
        this.flavorA = Integer.parseInt(st.nextToken());
        this.flavorB = Integer.parseInt(st.nextToken());
    }
    int flavorA;
    int flavorB;
}

public class Main {
    static int result = Integer.MAX_VALUE;
    public static void dfs(Food[] foods, int n, int depth, boolean[] used) {
        if (depth == n) {
            int multiOfFlavorA = 1;
            int sumOfFlavorB = 0;

            for (int i = 0 ; i < n ; i ++) {
                if (used[i]) {
                    multiOfFlavorA *= foods[i].flavorA;
                    sumOfFlavorB += foods[i].flavorB;
                }
            }

            if (multiOfFlavorA != 1 && sumOfFlavorB != 0) {
                int diff = multiOfFlavorA - sumOfFlavorB;
                if (diff < 0) diff *= -1;

                result = Math.min(result, diff);
            }
        } else {
            used[depth] = true;
            dfs(foods, n, depth+1, used);
            used[depth] = false;
            dfs(foods, n, depth+1, used);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Food[] foods = new Food[N];
        for (int i = 0 ; i < N ; i ++) {
            foods[i] = new Food(br.readLine());
        }

        dfs(foods, N, 0, new boolean[N]);

        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
