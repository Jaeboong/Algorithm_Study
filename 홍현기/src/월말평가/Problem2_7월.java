package 월말평가;

import java.util.*;
import java.io.*;


public class Problem2_7월 {

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("Test2.txt"));
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		
		for(int t = 1; t <= test; t++) {
			int n = sc.nextInt();
			int[][] arr = new int[n+1][n+1];
			
			int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
			int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
			
			int doal = sc.nextInt();
			int[] him1 = new int[doal];
			
			int max = 0;
			
			for(int d = 0; d < doal; d++) {
				him1[d] = sc.nextInt();
			}
			for(int d = 0; d < doal; d++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int him = him1[d];
				
				arr[x][y] += 1;
				
				for(int i = 0; i<8; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					for(int j = 0; j<him; j++) {
						if(nx < 0 || nx >=n || ny < 0 || ny >= n) break;
						
						arr[nx][ny] += 1;
						nx = nx + dx[i];
						ny = ny + dy[i];
						
					}
					
				}
				
				
				
				
			}
			
			
			for(int i = 0; i<n; i++) {
				for(int j = 0; j<n; j++) {
					if (arr[i][j] > max) {
						max = arr[i][j];
					}
				}
			}
			
			
			
			System.out.println("#"+t+ " " + max);
		}
		
		
		
		
		
		
	}

}
