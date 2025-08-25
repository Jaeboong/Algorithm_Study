package com.kdedevelop.acmicpc.P2623;

import java.io.*;
import java.util.*;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < N ; i ++) {
            if (edgeSize[i] == 0) queue.offer(i);
        }

        while (true) {
            if (queue.isEmpty()) break;

            int curr = queue.poll();
            result.add(curr);
            for (int next : SINGER[curr]) {
                if (--edgeSize[next] == 0) queue.offer(next);
            }
        }

        return result;
    }

    int N;
    int M;
    List<Integer>[] SINGER;
    int[] edgeSize;
    public void solution() throws IOException {
        StringTokenizer inputNM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(inputNM.nextToken());
        M = Integer.parseInt(inputNM.nextToken());
        SINGER = new List[N];
        edgeSize = new int[N];
        for (int i = 0 ; i < N ; i ++) SINGER[i] = new ArrayList<>();
        for (int i = 0 ; i < M ; i ++) {
            StringTokenizer inputLine = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(inputLine.nextToken());
            int singer = Integer.parseInt(inputLine.nextToken())-1;
            for (int j = 0 ; j < count-1 ; j ++) {
                int nextSinger = Integer.parseInt(inputLine.nextToken())-1;
                SINGER[singer].add(nextSinger);
                singer = nextSinger;
                edgeSize[nextSinger] ++;
            }
        }

//        for (int i = 0 ; i < N ; i ++) {
//            System.out.println(Arrays.toString(SINGER[i].toArray()));
//        }

        List<Integer> result = bfs();

        if (result.size() != N) {
            bw.write(String.valueOf(0));
        } else {
            for (int temp : result) {
                bw.write((temp + 1) + "\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
