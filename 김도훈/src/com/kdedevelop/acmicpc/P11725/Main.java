package com.kdedevelop.acmicpc.P11725;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] nods = new LinkedList[N+1];
		
		for (int i = 0; i < N; i++) {
            nods[i] = new LinkedList<>();
        }
		
		for (int i = 0 ; i < N-1 ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			nods[a].add(b);
			nods[b].add(a);
		}
		
		int[] result = new int[N];
		boolean[] visit = new boolean[N];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		
		while (true) {
			if (queue.isEmpty()) break;
			int num = queue.poll();
			visit[num] = true;
			List<Integer> node = nods[num];
			
			for (int temp : node) {
				if (visit[temp]) continue;
				result[temp] = num;
				queue.add(temp);
			}
		}
		
		for (int i = 1 ; i < N ; i ++) {
			bw.write((result[i] + 1) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
