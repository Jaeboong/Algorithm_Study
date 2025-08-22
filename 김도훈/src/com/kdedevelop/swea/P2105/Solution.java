package com.kdedevelop.swea.P2105;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

    public boolean isOutOfMatrix(Point point) {
        if (point.x < 0 || point.x >= N) return true;
        if (point.y < 0 || point.y >= N) return true;
        return false;
    }

    public boolean isVisit(Point point) {
        return visit[point.y][point.x];
    }

    public boolean isAlreadyEat(int[] data, Point point, int depth) {
        for (int i = 0 ; i <= depth ; i ++) {
            if (data[i] == DESSERT[point.y][point.x]) return true;
        }
        return false;
    }

    int[] dirX = {1, 1, -1, -1};
    int[] dirY = {-1, 1, 1, -1};

    public void dfs(Point start, int depth, Point current, int[] data, int turnCount, int turnDir, int currDir) {
//        System.out.println("DEPTH : " + depth);
        if (turnCount == 4) return;

//            System.out.println("X : " + current.x + " || Y : " + current.y);

        data[depth] = DESSERT[current.y][current.x];

        visit[current.y][current.x] = true;
        int[] dirArray = {currDir, ((currDir + turnDir) + 4) % 4};
//            System.out.println(turnDir + " || " + Arrays.toString(dirArray));
//            System.out.println("DIR : " + dirX[dirArray[0]] + ", " + dirY[dirArray[0]] + " || NEXT DIR : " + dirX[dirArray[1]] + ", " + dirY[dirArray[1]]);
        for (int i = 0 ; i < 2 ; i ++) {
            int dir = dirArray[i];

            Point next = new Point(current.x + dirX[dir], current.y + dirY[dir]);

//            System.out.println("CURRENT : " + current.x + ", " + current.y + " || NEXT : " + next.x + ", " + next.y);

            if (isOutOfMatrix(next)) {
//                System.out.println("FAIL OUT OF MATRIX");
                continue;
            }

            if (start.x == next.x && start.y == next.y) {
//                System.out.println("?");
                if (result < depth+1) {
                    result = depth+1;

//                    System.out.println(Arrays.toString(data));
//                    for (boolean[] visited : visit) {
//                        System.out.println(Arrays.toString(visited));
//                    }

                }
            }

            if (isVisit(next)) {
//                System.out.println("FAIL ALREADY VISIT");
                continue;
            }

            if (isAlreadyEat(data, next, depth)) {
//                System.out.println("ALREADY EAT");
                continue;
            }

            dfs(start, depth + 1, next, data, turnCount + i, turnDir, dir);
        }
        visit[current.y][current.x] = false;
    }

    int result;
    int N;
    int[][] DESSERT;
    boolean[][] visit;
    public void solution() throws IOException {
        int TotalTestCase = Integer.parseInt(br.readLine());

        for (int testCase = 0 ; testCase < TotalTestCase ; testCase++) {
            result = -1;
            N = Integer.parseInt(br.readLine());
            DESSERT = new int[N][N];
            visit = new boolean[N][N];
            for (int i = 0 ; i < N ; i ++) {
                StringTokenizer inputLine = new StringTokenizer(br.readLine());
                for (int j = 0 ; j < N ; j ++) {
                    DESSERT[i][j] = Integer.parseInt(inputLine.nextToken());
                }
            }

//            System.out.println("N : " + N);

//            System.out.println("");

            for (int i = 0 ; i < N ; i ++) {
//                System.out.println("I : " + i);
                for (int j = 0 ; j < N ; j ++) {
//                    System.out.println("J : " + j);
//                    System.out.println("X : " + j + " || Y : " + i);
//                    if (j != 2 || i != 0) continue;
                    Point start = new Point(j, i);
                    for (int dir = 0 ; dir < 4 ; dir ++) {
//                        System.out.println("DIR : " + dir);
                        for (int turnDir = -1 ; turnDir < 2 ; turnDir += 2) {
//                            System.out.println("TURN DIR : " + turnDir);
                            dfs(start, 0, start, new int[((N-1)*2)], 0, turnDir, dir);
                        }
                    }
                }
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
