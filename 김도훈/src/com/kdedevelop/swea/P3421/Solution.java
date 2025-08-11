package com.kdedevelop.swea.P3421;

import java.io.*;
import java.util.*;

public class Solution {
    static int result;
    static void dfs(int N, Map<Integer, Set<Integer>> declineList, int depth, boolean[] used) {
        if (depth == N) {
            //System.out.println(Arrays.toString(used));
            for (int i = 0 ; i < N ; i ++) {
                if (!used[i]) continue;
                //System.out.print(i + " WITH ");
                Set<Integer> set = declineList.get(i);
                if (set == null) continue;
                for (int decline : set) {
                    //System.out.print(decline + " is : ");
                    if (used[decline]) {
                        //System.out.println("DECLINE");
                        return;
                    } else {
                        //System.out.println("NOT DECLINE");
                    }
                }
            }
            result ++;
        } else {
            used[depth] = true;
            dfs(N, declineList, depth + 1, used);
            used[depth] = false;
            dfs(N, declineList, depth + 1, used);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TotalTestCase = Integer.parseInt(br.readLine());

        for (int testCase = 0 ; testCase < TotalTestCase ; testCase++) {
            result = 0;
            StringTokenizer inputNM = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(inputNM.nextToken());
            int M = Integer.parseInt(inputNM.nextToken());

            Map<Integer, Set<Integer>> declineList = new HashMap<>();

            for (int i = 0 ; i < M ; i ++) {
                StringTokenizer input = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(input.nextToken())-1;
                int b = Integer.parseInt(input.nextToken())-1;

                if (!declineList.containsKey(a)) declineList.put(a, new HashSet<>());
                if (!declineList.containsKey(b)) declineList.put(b, new HashSet<>());
                declineList.get(a).add(b);
                declineList.get(b).add(a);
            }

            dfs(N, declineList, 0, new boolean[N]);

            bw.write("#" + (testCase+1) + " " + result + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}