package com.kdedevelop.acmicpc.P1941;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public boolean isS(int index) {
        int y = index / 5;
        int x = index % 5;
        return INPUT[y][x];
    }

//    public boolean isConnected(int depth, int[] data, int target) {
//        for(int i = 0 ; i < depth ; i ++) {
//            int prev = data[i];
//            if (prev + 1 == target) return true;
//            if (prev - 1 == target) return true;
//            if (prev + 5 == target) return true;
//            if (prev - 5 == target) return true;
//        }
//        return false;
//    }

    public boolean isAllConnected(int[] data) {
        // 선택된 7개 좌표를 set에 저장
        Set<Integer> set = new HashSet<>();
        for (int idx : data) set.add(idx);

        // BFS 시작
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(data[0]);
        visited.add(data[0]);

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int cur = q.poll();
            int r = cur / 5, c = cur % 5;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                int nxt = nr * 5 + nc;
                if (set.contains(nxt) && !visited.contains(nxt)) {
                    visited.add(nxt);
                    q.add(nxt);
                }
            }
        }

        return visited.size() == 7; // 7개 전부 도달했으면 true
    }

    int result;
    public void dfs(int depth, int prev, int[] data, int countY) {
        if(depth == 7) {
            if(countY > 3) return;
            if(!isAllConnected(data)) return;
            result ++;
        } else {
            for(int i = prev + 1 ; i < 25 ; i++) {
//                if(!isConnected(depth, data, i)) continue;
                boolean s = isS(i);
                data[depth] = i;
                dfs(depth + 1, i, data, countY + (s ? 0 : 1));
            }
        }
    }

    boolean[][] INPUT = new boolean[5][5];
    public void solution() throws IOException {
        result = 0;
        for(int i = 0 ; i < 5 ; i ++) {
            String line = br.readLine();
            for(int j = 0 ; j < 5 ; j ++) {
                INPUT[i][j] = line.charAt(j) == 'S';
            }
        }

//        for (int start = -1 ; start < 24 ; start ++) {
            dfs(0, -1, new int[7], 0);
//        }

        bw.write(String.valueOf(result));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
