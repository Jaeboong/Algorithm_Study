package com.kdedevelop.swea.P7699;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] dirX = {0, 0, -1, 1};
    int[] dirY = {-1, 1, 0, 0};

    public boolean isOutOfIsland(int x, int y) {
        if(x < 0 || x >= C) return true;
        if(y < 0 || y >= R) return true;
        return false;
    }

    long result;
    public void dfs(int x, int y, int count) {
        result = Math.max(result, count);
        char chr = ISLAND[y][x];
        int chrIndex = chr - 'A';

//        System.out.println("X : " + x + " || Y: " + y + " || count : " + count + " || CHAR : " + chr);
//        System.out.println(Arrays.toString(visit));

        visit[chrIndex] = true;
        for(int dir = 0 ; dir < 4 ; dir ++) {
            int nextX = x + dirX[dir];
            int nextY = y + dirY[dir];
//            System.out.println("NEXT X : " + nextX + " || NEXT Y : " + nextY);

            if(isOutOfIsland(nextX, nextY)) {
//                System.out.println("IS OUT");
                continue;
            }

            char nextChr = ISLAND[nextY][nextX];
//            System.out.println("NEXT CHAR : " + nextChr);
            int nextChrIndex = nextChr - 'A';
            if(visit[nextChrIndex]) {
//                System.out.println("IS ALREADY WATCH");
                continue;
            }

            dfs(nextX, nextY, count + 1);
        }
        visit[chrIndex] = false;
    }

    int R;
    int C;
    char[][] ISLAND;
    boolean[] visit;
    public void solution() throws IOException {
        int TotalTestCase = Integer.parseInt(br.readLine());
        for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
//            System.out.println("===========");
            result = 0;
            StringTokenizer inputRC = new StringTokenizer(br.readLine());
            R = Integer.parseInt(inputRC.nextToken());
            C = Integer.parseInt(inputRC.nextToken());
            ISLAND = new char[R][C];
            visit = new boolean[26];
            for(int i = 0 ; i < R ; i ++) {
                String line = br.readLine();
                for(int j = 0 ; j < C ; j ++) {
                    char chr = line.charAt(j);
                    ISLAND[i][j] = chr;
                }
            }

            dfs(0, 0, 1);

            bw.write("#" + (testCase + 1) + " " + result + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }
}
