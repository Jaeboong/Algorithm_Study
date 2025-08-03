package 월말평가;

import java.util.*;
import java.io.*;

public class Problem4_7월 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		int[][] arr = new int[n][m];
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j< Math.min(n, m)/2; j++) {
				
				int temp = arr[j][j];  
				
		
				for(int k=j; k<m-j-1; k++) {
					arr[j][k] = arr[j][k+1];
				}
	
				for(int k=j; k<n-1-j; k++) {
					arr[k][m-j-1] = arr[k+1][m-j-1];
				}
			
				for(int k=m-j-1; k>j; k--) {
					arr[n-1-j][k] = arr[n-1-j][k-1];
				}
				
				for(int k=n-j-1; k>j; k--) {
					arr[k][j] = arr[k-1][j];
				}
				arr[j+1][j] = temp;
			}
		}
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<m;j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
		
		
		
		
		
		
	}
}