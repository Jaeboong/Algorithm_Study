package com.kdedevelop.acmicpc.P12891;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static boolean isWork(Map<Character, Integer> counter, int A, int C, int G, int T) {
        if (counter.get('A') < A) return false;
        if (counter.get('C') < C) return false;
        if (counter.get('G') < G) return false;
        if (counter.get('T') < T) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer inputSP = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(inputSP.nextToken());
        int P = Integer.parseInt(inputSP.nextToken());

        String input = br.readLine();

        Map<Character, Integer> counter = new HashMap<>();
        counter.put('A', 0);
        counter.put('C', 0);
        counter.put('G', 0);
        counter.put('T', 0);

        StringTokenizer inputACGTCounter = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(inputACGTCounter.nextToken());
        int C = Integer.parseInt(inputACGTCounter.nextToken());
        int G = Integer.parseInt(inputACGTCounter.nextToken());
        int T = Integer.parseInt(inputACGTCounter.nextToken());

        int result = 0;

        for (int i = 0 ; i < P ; i ++) {
            char chr = input.charAt(i);
            //bw.write("ADD : " + chr + " ");
            counter.put(chr, counter.get(chr) + 1);
        }
        //bw.write("\n");

        if (isWork(counter, A, C, G, T)) result ++;

        for (int i = P ; i < S ; i++) {
            char addChr = input.charAt(i);
            //bw.write("ADD : " + addChr + " ||");
            counter.put(addChr, counter.get(addChr) + 1);

            char removeChr = input.charAt(i-P);
            //bw.write("REMOVE : " + removeChr + "\n");
            counter.put(removeChr, counter.get(removeChr) - 1);

            if (isWork(counter, A, C, G, T)) result ++;
        }

        bw.write(result + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}
