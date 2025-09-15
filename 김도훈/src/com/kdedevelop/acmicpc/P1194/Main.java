package com.kdedevelop.acmicpc.P1194;

import java.io.*;
import java.util.*;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] dirX = {0, 0, -1, 1};
    int[] dirY = {-1, 1, 0, 0};

    public boolean isOutOfMap(int x, int y) {
        if(x < 0 || x >= M) return true;
        if(y < 0 || y >= N) return true;
        return false;
    }

    public int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visit = new boolean[N][M][1<<6 + 1];
        queue.offer(START);
        visit[START[1]][START[0]][0] = true;

        int depth = 0;
        while(true) {
            if(queue.isEmpty()) break;

            int size = queue.size();

            for(int i = 0 ; i < size ; i ++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];

//                System.out.println("X : " + x + " || Y : " + y + " || KEY : " + curr[2]);

                char currObj = MAP[y][x];
                if(currObj == '1') {
                    return depth;
                }

                for(int dir = 0 ; dir < 4 ; dir ++) {
                    int key = curr[2];
                    int nextX = x + dirX[dir];
                    int nextY = y + dirY[dir];

//                    System.out.println("NEXT X : " + nextX + " || NEXT Y : " + nextY);

                    if(isOutOfMap(nextX, nextY)) {
//                        System.out.println("IS OUT OF MAP");
                        continue;
                    }
                    if(visit[nextY][nextX][key]) {
//                        System.out.println("IS ALREADY VISIT");
                        continue;
                    }

                    char nextObj = MAP[nextY][nextX];

                    if(nextObj == '#') continue;
                    if(nextObj >= 'a' && nextObj <= 'z') {
                        int keyNumber = (nextObj - 'a') + 1;
                        key |= (1 << keyNumber);
                    }
                    if(nextObj >= 'A' && nextObj <= 'Z') {
                        int keyNumber = (nextObj - 'A') + 1;
                        if((key & (1 << keyNumber)) == 0) continue;
                    }

                    queue.offer(new int[] {nextX, nextY, key});
                    visit[nextY][nextX][key] = true;
                }
            }

            ++ depth;
        }

        return -1;
    }

    int N, M;
    char[][] MAP;
    int[] START;
    public void solution() throws IOException {
//        System.out.println(('a' - 0) + " | " + ('z' - 0));
//        System.out.println(('A' - 0) + " | " + ('Z' - 0));
//        System.out.println('d' - 'b');
//        System.out.println(1 << ('d' - 'a'));
        StringTokenizer inputNM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(inputNM.nextToken());
        M = Integer.parseInt(inputNM.nextToken());

//        System.out.println("N : " + N + " || M : " + M);

        MAP = new char[N][M];
//        Set<Character> keyType = new HashSet<>();
        for(int y = 0 ; y < N ; y ++) {
            String line = br.readLine();
            for(int x = 0 ; x < M ; x ++) {
                char value = line.charAt(x);
                if(value == '0') START = new int[] {x, y, 0};
                MAP[y][x] = value;
            }
        }

        int result = bfs();

        bw.write(String.valueOf(result));

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
