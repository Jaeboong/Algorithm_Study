package com.kdedevelop.swea.P1949;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}

public class Solution {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] dirX = {0, 0, -1, 1};
    int[] dirY = {-1, 1, 0, 0};

    boolean isOutOfMatrix(int x, int y) {
        if (x < 0 || x >= N) return true;
        if (y < 0 || y >= N) return true;
        return false;
    }

    void dfs(int x, int y, int length, boolean[][] visited, boolean isMining) {
        visited[y][x] = true;
        if (result < length) {
            result = length;
//            for (int i = 0 ; i < N ; i ++) {
//                for (int j = 0 ; j < N ; j ++) {
//                    System.out.print((visited[i][j] ? "0" : "1") + " ");
//                }
//                System.out.println("");
//            }
//            System.out.println("");
        }
        int currentHeight = MAP[y][x];

        for (int dir = 0 ; dir < 4 ; dir ++) {
            int nextX = x + dirX[dir];
            int nextY = y + dirY[dir];

            if (isOutOfMatrix(nextX, nextY)) continue;
            if (visited[nextY][nextX]) continue;

            int nextHeight = MAP[nextY][nextX];

            if (nextHeight >= currentHeight) {
                if (!isMining) {
                    for (int i = 1 ; i <= K ; i ++) {
                        int minedNextHeight = nextHeight - i;
                        if (minedNextHeight < currentHeight) {
                            MAP[nextY][nextX] = minedNextHeight;
                            dfs(nextX, nextY, length+1, visited, true);
                            MAP[nextY][nextX] = nextHeight;
                        }
                    }
                }
            } else {
                dfs(nextX, nextY, length+1, visited, isMining);
            }
        }
        visited[y][x] = false;
    }

    int result;
    int N;
    int K;
    int[][] MAP;
    public void solution() throws IOException {
        int TotalTestCase = Integer.parseInt(br.readLine());

        for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
//            System.out.println("TEST CASE : " + testCase);
            StringTokenizer inputNK = new StringTokenizer(br.readLine());
            N = Integer.parseInt(inputNK.nextToken());
            K = Integer.parseInt(inputNK.nextToken());
            MAP = new int[N][N];
            int highest = Integer.MIN_VALUE;
            List<Point> highestPoint = new LinkedList<>();
            for (int i = 0 ; i < N ; i ++) {
                StringTokenizer lineInput = new StringTokenizer(br.readLine());
                for (int j = 0 ; j < N ; j ++) {
                    int height = Integer.parseInt(lineInput.nextToken());
                    MAP[i][j] = height;
                    if (highest < height) {
                        highest = height;
                        highestPoint.clear();
                    }
                    if (highest == height) highestPoint.add(new Point(j, i));
                }
            }
//            System.out.println("MAX : " + highest);

            result = 0;

            for (Point point : highestPoint) {
                int x = point.x;
                int y = point.y;
//                System.out.println("X : " + x + " || Y : " + y);
                dfs(x, y, 1, new boolean[N][N], false);
            }

            bw.write("#" + (testCase+1) + " " + result + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }
}


