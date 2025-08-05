package com.algo.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2477 {
	static int n;
	static int[] dir;
	static int[] len;
	static int mx_h = -1;
	static int mx_w = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		dir = new int[6];
		len = new int[6];
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			dir[i] = Integer.parseInt(st.nextToken());
			len[i] = Integer.parseInt(st.nextToken());
			if(dir[i] == 1 || dir[i] == 2) {
				mx_w = Math.max(mx_w, len[i]);
			}else {
				mx_h = Math.max(mx_h, len[i]);
			}
		}
		int b = mx_h * mx_w;
		int s = 0;
		for(int i = 0; i < 6; i++) {
			if(dir[i] == dir[(i + 2) % 6] && dir[(i + 1) % 6] == dir[(i + 3) % 6]) {
				s = len[(i + 1) % 6] * len[(i + 2) % 6];
			}
		}
		System.out.println((b - s) * n);
	}

}
