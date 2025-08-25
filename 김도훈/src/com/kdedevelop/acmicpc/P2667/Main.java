package com.kdedevelop.acmicpc.P2667;

import java.io.*;
import java.util.*;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] dirX = {0, 0, 1, -1};
    int[] dirY = {1, -1, 0, 0};

    public boolean isOutOfMap(int x, int y) {
        if (x < 0 || x >= N) return true;
        if (y < 0 || y >= N) return true;
        return false;
    }

    public void dfs(int x, int y, int groupNumber, int depth) {
        visited[y][x] = true;
        groupSize.set(groupNumber, groupSize.get(groupNumber) + 1);
        for (int i = 0 ; i < 4 ; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (isOutOfMap(nextX, nextY)) continue;
            if (visited[nextY][nextX]) continue;
            if (!MAP[nextY][nextX]) continue;

            dfs(nextX, nextY, groupNumber, depth + 1);
        }
    }

    int N;
    boolean[][] MAP;

    boolean[][] visited;
    List<Integer> groupSize;
    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        MAP = new boolean[N][N];
        visited = new boolean[N][N];
        groupSize = new ArrayList<>();
        for (int y = 0; y < N; y++) {
            String line = br.readLine();
            for (int x = 0; x < N; x++) {
                MAP[y][x] = line.charAt(x) == '1';
            }
        }

        int result = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (visited[y][x]) continue;
                if (!MAP[y][x]) continue;

                int groupNumber = result ++;
                groupSize.add(0);
                dfs(x, y, groupNumber, 0);
            }
        }

        Collections.sort(groupSize);
        bw.write(String.valueOf(result) + "\n");
        for (int size : groupSize) bw.write(String.valueOf(size) + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
