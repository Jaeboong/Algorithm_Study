package com.kdedevelop.acmicpc.P2636;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(int x, int y, int degree) {
        this(x, y);
        this.degree = degree;
    }
    int x;
    int y;
    int degree;
}

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] dirX = {0, 0, 1, -1};
    int[] dirY = {1, -1, 0, 0};

    public boolean isOutOfBoard(int x, int y) {
        if (x < 0 || x >= X) return true;
        if (y < 0 || y >= Y) return true;
        return false;
    }

    public boolean isOutOfBoard(Point p) {
        return isOutOfBoard(p.x, p.y);
    }

    public int bfs(int time) {
        boolean[][] visit = new boolean[Y][X];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        visit[0][0] = true;
        int count = 0;
        while(true) {
            if (queue.isEmpty()) break;

            Point curr = queue.poll();

            for(int dir = 0 ; dir < 4 ; dir ++) {
                Point next = new Point(curr.x + dirX[dir], curr.y + dirY[dir]);

                if (isOutOfBoard(next)) continue;
                if (visit[next.y][next.x]) continue;

                visit[next.y][next.x] = true;
                if (BOARD[next.y][next.x]) {
                    if (BOARD_COUNTER[next.y][next.x] == -1) {
                        BOARD_COUNTER[next.y][next.x] = time;
                        ++ count;
                    } else {
                        queue.offer(next);
                    }
                } else {
                    queue.offer(next);
                }
            }
        }
        return count;
    }

    int Y;
    int X;
    boolean[][] BOARD;
    int[][] BOARD_COUNTER;
    int CHEESE_COUNT = 0;
    public void solution() throws IOException, InterruptedException {
        StringTokenizer inputYX = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(inputYX.nextToken());
        X = Integer.parseInt(inputYX.nextToken());
        BOARD = new boolean[Y][X];
        BOARD_COUNTER = new int[Y][X];
        for(int y = 0 ; y < Y ; y++) {
            StringTokenizer inputLine = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < X ; x++) {
                boolean cheese = inputLine.nextToken().charAt(0) == '1';
                BOARD[y][x] = cheese;
                BOARD_COUNTER[y][x] = -1;
                if (cheese) ++ CHEESE_COUNT;
            }
        }

//        System.out.println(CHEESE_COUNT);

        int time = 0;
        int count = 0;

        while(true) {
            if (CHEESE_COUNT == 0) break;
            count = bfs(time);
            CHEESE_COUNT -= count;

//            System.out.println("=============================");
//            for(int i = 0 ; i < Y ; i ++) {
//                for(int j = 0 ; j < X ; j ++) {
//                    System.out.print((BOARD_COUNTER[i][j] == Integer.MAX_VALUE ? "X" : BOARD_COUNTER[i][j]) + " ");
////                    System.out.print((BOARD[i][j] ? "1" : "0") + " ");
//                }
//                System.out.println("");
//            }
//            System.out.println("COUNT : " + CHEESE_COUNT);
//            System.out.println("TIME : " + time);
//
//            Thread.sleep(1000);

            ++ time;
        }

        bw.write(String.valueOf(time) + "\n" + String.valueOf(count));

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Main().solution();
    }
}
