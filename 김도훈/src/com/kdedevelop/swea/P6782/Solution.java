package com.kdedevelop.swea.P6782;

import java.io.*;

public class Solution {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long N;
    long result;
    public void solution() throws IOException {
        int TotalTestCase = Integer.parseInt(br.readLine());
        for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
//            System.out.println("======");
            result = 0;

            N = Long.parseLong(br.readLine());

            while (true) {
//                System.out.println(N + " ");
                if (N > 2) {
                    long sqrt = (long) Math.sqrt(N);
                    if (Math.pow(sqrt, 2) == N) {
                        result ++;
//                        System.out.println("SQRT : " + sqrt);
                        N = sqrt;
                    } else {
                        long temp = (sqrt + 1) * (sqrt + 1);
                        result += temp - N;
                        N = temp;
                    }
                } else if (N < 2) {
                    result ++;
                    N += 1;
                } else {
                    break;
                }
            }

            bw.write("#" + (testCase + 1) + " " + String.valueOf(result) + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }
}
