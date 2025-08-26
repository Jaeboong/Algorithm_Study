package com.kdedevelop.acmicpc.P15686;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public int getDistance(int[] a, int[] b) {
        int distX = a[0] - b[0];
        if(distX < 0) distX *= -1;
        int distY = a[1] - b[1];
        if(distY < 0) distY *= -1;

        return distX + distY;
    }

    int[] parents;
    int result;
    public void dfs(int depth, int start, int[] data) {
        if(depth == M) {
//            System.out.println("DEPTH : " + depth + " || START : " + start + " || DATA : " + Arrays.toString(data));
            parents = new int[M];
            for(int i = 0 ; i < M ; i ++) parents[i] = i;

            int sumOfDistance = 0;
            for(int[] house : HOUSE) {
                int shopDistance = Integer.MAX_VALUE;
                for(int shopIndex : data) {
                    int[] shop = SHOP.get(shopIndex);
                    int distance = getDistance(house, shop);
                    shopDistance = Math.min(shopDistance, distance);
//                    System.out.println(shopDistance);
                }
                sumOfDistance += shopDistance;
            }
            result = Math.min(result, sumOfDistance);
        } else {
            for(int i = start + 1 ; i < SHOP.size() ; i ++) {
                data[depth] = i;
                dfs(depth + 1, i, data);
            }
        }
    }

    int N;
    int M;
    int[][] CITY;
    List<int[]> HOUSE;
    List<int[]> SHOP;
    public void solution() throws IOException {
        StringTokenizer inputNM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(inputNM.nextToken());
        M = Integer.parseInt(inputNM.nextToken());
        CITY = new int[N][N];
        HOUSE = new ArrayList<>(N * 2);
        SHOP = new ArrayList<>(13);
        result = Integer.MAX_VALUE;
        for(int i = 0 ; i < N ; i ++) {
            StringTokenizer inputLine = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++) {
                int input = Integer.parseInt(inputLine.nextToken());
                if(input == 2) {
                    int[] shop = {j, i};
                    SHOP.add(shop);
                }
                if (input == 1) {
                    int[] house = {j, i};
                    HOUSE.add(house);
                }
                CITY[i][j] = input;
            }
        }

        dfs(0, -1, new int[M]);

        bw.write(String.valueOf(result));

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
