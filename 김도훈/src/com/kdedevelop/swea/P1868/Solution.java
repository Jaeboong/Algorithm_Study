package com.kdedevelop.swea.P1868;

import java.io.*;
import java.util.*;

public class Solution {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//    int[] dirX = {0, 0, 1, 1, 1, -1, -1, -1};
//    int[] dirY = {1, -1, 1, 0, -1, 1, 0, -1};

    int[] dirX = {0, 0, -1, 1, 1, 1, -1, -1};
    int[] dirY = {1, -1, 0, 0, 1, -1, 1, -1};

    public boolean isOutOfMap(int x, int y) {
        if(x < 0 || x >= N) return true;
        if(y < 0 || y >= N) return true;
        return false;
    }

    public int[] converter(int comp) {
        int y = comp / N;
        int x = comp % N;
        return new int[] {x, y};
    }

    public int converter(int x, int y) {
        return (N * y) + x;
    }

    public int find(int comp) {
        if(parents[comp] != comp) parents[comp] = find(parents[comp]);
        return parents[comp];
    }

    public boolean union(int comp1, int comp2) {
        int parent1 = find(comp1);
        int parent2 = find(comp2);

        if(parent1 == parent2) return false;

        int[] xy1 = converter(parent1);
        parents[parent1] = parent2;
        return true;
    }

    int N;
    boolean[][] MINE_MAP;

    int clickCount;
    int[][] mineCountMap;
    int[] parents;
    public void solution() throws IOException {
        int TotalTestCase = Integer.parseInt(br.readLine());
        for(int testCase = 0 ; testCase < TotalTestCase ; testCase++) {
            N = Integer.parseInt(br.readLine());
            MINE_MAP = new boolean[N][N];
            clickCount = N * N;
            mineCountMap = new int[N][N];
            parents = new int[N * N];
            for(int i = 0 ; i < N ; i ++) {
                String line = br.readLine();
                for(int j = 0 ; j < N ; j ++) {
                    parents[converter(j, i)] = -1;
                    boolean mine = line.charAt(j) == '*';
                    MINE_MAP[i][j] = mine;
                    if(mine) mineCountMap[i][j] = -1;
                }
            }

            for(int i = 0 ; i < N ; i ++) {
                for(int j = 0 ; j < N ; j ++) {
                    if(MINE_MAP[i][j]) {
                        -- clickCount;
                        for(int dir = 0 ; dir < 8 ; dir ++) {
                            int nextX = j + dirX[dir];
                            int nextY = i + dirY[dir];

                            if(isOutOfMap(nextX, nextY)) continue;
                            if(MINE_MAP[nextY][nextX]) continue;

                            ++ mineCountMap[nextY][nextX];
                        }
                    } else {
                        int comp = converter(j, i);
                        parents[comp] = comp;
                    }
                }
            }

//            for(int i = 0 ; i < N ; i ++) {
//                for(int j = 0 ; j < N ; j ++) {
//                    int comp = converter(j, i);
//                    System.out.printf("%2s ", (parents[comp] == -1 ? "X" : parents[comp]));
//                }
//                System.out.println("");
//            }
//            System.out.println("");


            boolean[][] visit = new boolean[N][N];
            Queue<int[]> queue = new LinkedList<>();
            for(int y = 0 ; y < N ; y ++) {
                for(int x = 0 ; x < N ; x ++) {
                    if(MINE_MAP[y][x]) continue;
                    if(mineCountMap[y][x] > 0) continue;
                    if(visit[y][x]) continue;

                    queue.offer(new int[] {x, y});
                    visit[y][x] = true;

                    while(true) {
                        if(queue.isEmpty()) break;

                        int[] xy = queue.poll();
                        if(union(converter(x, y), converter(xy[0], xy[1]))) clickCount --;

                        for(int dir = 0 ; dir < 8 ; dir ++) {
                            int nextX = xy[0] + dirX[dir];
                            int nextY = xy[1] + dirY[dir];

                            if(isOutOfMap(nextX, nextY)) continue;
                            if(MINE_MAP[nextY][nextX]) continue;
                            if(visit[nextY][nextX]) continue;
                            if(mineCountMap[nextY][nextX] > 0) {
                                if(union(converter(x, y), converter(nextX, nextY))) clickCount --;
                                visit[nextY][nextX] = true;
                                continue;
                            }

                            queue.offer(new int[] {nextX, nextY});
                            visit[nextY][nextX] = true;
                        }
                    }
                }
            }

//            Set<Integer> set = new HashSet<>();
//            for(int i = 0 ; i < N ; i ++) {
//                for(int j = 0 ; j < N ; j ++) {
//                    if(mineCountMap[i][j] == -1) continue;
//                    set.add(find(converter(j, i)));
//                }
//            }
//            System.out.println(set.size());


//            for(int i = 0 ; i < N ; i ++) {
//                for(int j = 0 ; j < N ; j ++) {
//                    System.out.printf("%2s ", (mineCountMap[i][j]));
//                }
//                System.out.println("");
//            }
//            System.out.println("");
//
//            for(int i = 0 ; i < N ; i ++) {
//                for(int j = 0 ; j < N ; j ++) {
//                    int comp = converter(j, i);
//                    if(parents[comp] != -1) find(comp);
//                    System.out.printf("%4s ", (parents[comp] == -1 ? "X" : parents[comp]));
//                }
//                System.out.println("");
//            }

            bw.write("#" + (testCase + 1) + " " + clickCount + "\n");


        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }
}
