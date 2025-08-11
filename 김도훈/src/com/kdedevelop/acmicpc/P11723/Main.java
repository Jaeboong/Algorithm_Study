package com.kdedevelop.acmicpc.P11723;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(br.readLine());

        int bit = 0;
        for (int i = 0 ; i < M ; i ++) {
            StringTokenizer input =  new StringTokenizer(br.readLine());
            String operator = input.nextToken();

            switch (operator) {
                case "add" : {
                    int operand = Integer.parseInt(input.nextToken());
                    bit = (bit | (1 << operand));
                    break;
                }
                case "remove" : {
                    int operand = Integer.parseInt(input.nextToken());
                    bit = (bit & ~(1 << operand));
                    break;
                }
                case "check" : {
                    int operand = Integer.parseInt(input.nextToken());
                    int result = (bit & (1 << operand)) != 0 ? 1 : 0;
                    bw.write(result + "\n");
                    break;
                }
                case "toggle" : {
                    int operand = Integer.parseInt(input.nextToken());
                    bit = bit ^ (1 << operand);
                    break;
                }
                case "all" : {
                    bit = Integer.MAX_VALUE;
                    break;
                }
                case "empty" : {
                    bit = 0;
                    break;
                }
            }
            //System.out.println(bit);
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
