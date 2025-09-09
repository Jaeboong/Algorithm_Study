package LIS;

import java.io.*;
import java.util.*;

public class b25343 {
	
	static int[][] NN;
	static ArrayList<Integer>[] path;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		NN = new int[N][N];
		
		for(int i=0; i<N;i++) {
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				NN[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
	}

	
	
	private static void LIS() {
		
	}
}
