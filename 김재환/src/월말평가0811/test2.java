package 월말평가0811;

import java.io.*;
import java.util.*;


public class test2 {

	static int[][] NN;
	static int N;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		NN = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				NN[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		turn();
		
		drop();
	}
	
	static void turn() {
		int[][] tmp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = NN[N-j-1][i];
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				NN[i][j] = tmp[i][j];
			}
		}
	}
	
	static void drop() {
		int[] cnt = new int[N];
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(NN[j][i] == 1) cnt[i]++;
			}
		}
		
		int[][] tmp = new int[N][N];
	
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<cnt[i]; j++) {
				tmp[N-j-1][i] = 1;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(tmp[i][j]);
			}
			System.out.println();
		}
	}

}
