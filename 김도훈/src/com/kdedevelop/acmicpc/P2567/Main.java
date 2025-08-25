package com.kdedevelop.acmicpc.P2567;

import java.io.*;
import java.util.StringTokenizer;

class Paper {
    public Paper(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
}
public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int PAPER_COUNT;
    Paper[] PAPERS;
    boolean[][] CANVAS;
    public void solution() throws IOException {
        CANVAS = new boolean[101][101];
        PAPER_COUNT = Integer.parseInt(br.readLine());
        PAPERS = new Paper[PAPER_COUNT];
        for (int paperCount = 0 ; paperCount < PAPER_COUNT ; paperCount++) {
            StringTokenizer inputLine = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(inputLine.nextToken());
            int y = Integer.parseInt(inputLine.nextToken());

            for (int i = 0 ; i < 10 ; i ++) {
                int newY = 99 - y - i;
                for (int j = 0 ; j < 10 ; j ++) {
                    int newX = x + j;
                    CANVAS[newY][newX] = true;
                }
            }

            Paper paper = new Paper(x, y);
            PAPERS[paperCount] = paper;
        }

//        for (int i = 0 ; i < 100 ; i ++) {
//            for (int j = 0 ; j < 100 ; j ++) {
//                System.out.print((CANVAS[i][j] ? "0" : "1") + " ");
//            }
//            System.out.println("");
//        }

        int result = 0;

        for (int i = 0 ; i < 101 ; i ++) {
            boolean xColor = false;
            boolean yColor = false;
            for (int j = 0 ; j < 101 ; j ++) {
                if (CANVAS[i][j] != xColor) {
                    xColor = CANVAS[i][j];
                    result ++;
                }

                if (CANVAS[j][i] != yColor) {
                    yColor = CANVAS[j][i];
                    result ++;
                }
            }
        }

        bw.write(String.valueOf(result));

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
