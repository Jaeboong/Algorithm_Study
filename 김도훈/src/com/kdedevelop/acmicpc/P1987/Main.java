package com.kdedevelop.acmicpc.P1987;

import java.io.*;
import java.util.*;

class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] dirX = {0, 0, -1, 1};
    int[] dirY = {-1, 1, 0, 0};

    public boolean isOutOfBoard(Point p) {
        if (p.x < 0 || p.x >= C) return true;
        if (p.y < 0 || p.y >= R) return true;
        return false;
    }

    Set<Character> used;
    public void dfs(int depth, Point current) {
        result = Math.max(result, depth);
        char currentChar = BOARD[current.y][current.x];

        used.add(currentChar);
        for(int dir = 0 ; dir < 4 ; dir ++) {
            Point next = new Point(current.x + dirX[dir], current.y + dirY[dir]);
            if (isOutOfBoard(next)) continue;

            char nextChar = BOARD[next.y][next.x];
            if (used.contains(nextChar)) continue;

            dfs(depth + 1, next);
        }
        used.remove(currentChar);
    }

    int R;
    int C;
    char[][] BOARD;
    int result;
    public void solution() throws IOException {
        StringTokenizer inputRC = new StringTokenizer(br.readLine());
        R = Integer.parseInt(inputRC.nextToken());
        C = Integer.parseInt(inputRC.nextToken());
        BOARD = new char[R][C];
        used = new HashSet<>();
        result = 0;
        for(int i = 0 ; i < R ; i ++) {
            String line = br.readLine();
            for(int j = 0 ; j < C ; j ++) {
                BOARD[i][j] = line.charAt(j);
            }
        }

        dfs(0, new Point(0, 0));

        bw.write(String.valueOf(result+1));

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
