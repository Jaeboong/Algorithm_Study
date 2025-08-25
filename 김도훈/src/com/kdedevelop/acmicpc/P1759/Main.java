package com.kdedevelop.acmicpc.P1759;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public boolean isGather(char chr) {
        for(char gather : GATHER) if(gather == chr) return true;
        return false;
    }

    public void dfs(int depth, String password, int gatherCount, int consonantCount) throws IOException {
        if (depth == C) {
            if (password.length() != L) return;
            if (gatherCount < 1) return;
            if (consonantCount < 2) return;

            bw.write(password + "\n");
        } else {
            char current = INPUT_Cs[depth];
            boolean gather = isGather(current);

            dfs(depth + 1, password + INPUT_Cs[depth], gatherCount + (gather ? 1 : 0), consonantCount + (gather ? 0 : 1));
            dfs(depth + 1, password, gatherCount, consonantCount);
        }
    }

    int L;
    int C;
    char[] GATHER = {'a', 'e', 'i', 'o', 'u'};
    char[] INPUT_Cs;
    public void solution() throws IOException {
        StringTokenizer inputLC = new StringTokenizer(br.readLine());
        L = Integer.parseInt(inputLC.nextToken());
        C = Integer.parseInt(inputLC.nextToken());
        INPUT_Cs = new char[C];

        StringTokenizer inputCs = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < C ; i ++) {
            char chr = inputCs.nextToken().charAt(0);
            INPUT_Cs[i] = chr;
        }

        Arrays.sort(INPUT_Cs);

        dfs(0, "", 0, 0);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
