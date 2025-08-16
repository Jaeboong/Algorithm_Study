package 분할정복;

import java.io.*;
import java.util.*;


public class b1992 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] NN;
	String ans;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.parseInt(br.readLine());
		
		NN = new int[N][N];
		
		String[] tmp = new String[N];
		for(int i=0; i<N; i++) {
			tmp[i] = br.readLine();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				NN[i][j] = tmp[i].charAt(j) - '0';
			}
		}

		quadTree(0, 0, N);
		System.out.println(sb);
	}
	
	static void quadTree(int x, int y, int n) {
		
		int init = NN[y][x];
		
		for(int i=y; i<y+n; i++) {
			for(int j=x; j<x+n; j++) {
				if(init != NN[i][j]) {
					sb.append('(');
					quadTree(x, y, n/2);
					quadTree(x+n/2, y, n/2);
					quadTree(x, y+n/2, n/2);
					quadTree(x+n/2, y+n/2, n/2);
					sb.append(')');
					return;
				}
			}
		}
		
		if(init==1) sb.append('1');
		else sb.append('0');
		
	}

}
