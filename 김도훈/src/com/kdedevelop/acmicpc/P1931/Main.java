package com.kdedevelop.acmicpc.P1931;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Meeting {
    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
    int start;
    int end;
}

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    Meeting[] MEETINGs;
    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        MEETINGs = new Meeting[N];
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer line =  new StringTokenizer(br.readLine());
            int start = Integer.parseInt(line.nextToken());
            int end = Integer.parseInt(line.nextToken());
            Meeting meeting = new Meeting(start, end);
            MEETINGs[i] = meeting;
        }

        Arrays.sort(MEETINGs, (o1, o2) -> {
            int comp = Integer.compare(o1.end, o2.end);
            if (comp == 0) {
                return Integer.compare(o1.start, o2.start);
            } else {
                return comp;
            }
        });

        int result = 1;
        int time = MEETINGs[0].end;

        for (int i = 1 ; i < N ; i ++) {
            Meeting meeting = MEETINGs[i];
            if (time <= meeting.start) {
                ++ result;
                time = meeting.end;
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
