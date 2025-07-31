package 월말평가;

import java.util.*;
import java.io.*;

public class TEST4_7월 {
	

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Test4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[][] arr1 = new int[n][m];
		int[][] arr2 = new int[m][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) { arr1[i][j] = Integer.parseInt(st.nextToken()); }
		}
		
		for(int i=0; i<n; i++) {
//			for(int j=0; j<m; j++) System.out.print(arr1[i][j] + " ");
			System.out.println();
		}
		
		for(int k=0; k<=r; k++) {
			if(k%2==1 && k>0) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<m; j++) {
						arr2[j][i] = arr1[i][m-1-j];
					}
				}
//				System.out.println("홀회전");
			}
			
			if(k%2==0 && k>0){
				for(int i=0; i<m; i++) {
					for(int j=0; j<n; j++) {
						arr1[j][i] = arr2[i][n-1-j];
					}
				}
//				System.out.println("짝회전");
			}
		}
		
		System.out.println();
		
		if(r%2==1) {
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) System.out.print(arr2[i][j] + " ");
				System.out.println();
			}
		}
		else {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) System.out.print(arr1[i][j] + " ");
				System.out.println();
			}
		}
				
	}

}
