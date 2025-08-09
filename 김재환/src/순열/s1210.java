package 순열;

import java.util.*;
import java.io.*;

public class s1210 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("TEST.txt"));
		int result = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = 100;
		int m = 100;
		for(int test_case = 1; test_case <= 10; test_case++) {
			int T = Integer.parseInt(br.readLine());
			int arr[][] = new int[n][m];
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int idx2 = -1;
			
			for(int j=0; j<m; j++) {
				if(arr[n-1][j] == 2) idx2 = j;
			}
			
//			System.out.println(idx2);
			
			int y = 99;
			int x = idx2;
			while(y>0){
				if(0<x && x<99) {
					if(arr[y][x+1] == 1) {
						while(arr[y][x+1] != 0) {
//							System.out.println("x++ start " + x);
							x++;
							if(x==99) {
//								System.out.println("right wall " + x);
								break;
							}
							
//							System.out.println("x++" + x + " " + y);
						}
						if(x == 99 || arr[y][x+1] == 0) {
							y--;
//							System.out.println("Y--" + y);
						}
					}
					else if(arr[y][x-1] == 1) {
						while(arr[y][x-1] != 0) {
//							System.out.println("x-- start " + x);
							x--;
							if(x==0) {
//								System.out.println("left wall " + x);
								break;
							}
//							System.out.println("x--" + x + " " + y);
						}
						if(x==0 || arr[y][x-1] == 0) { 
							y--;
//							System.out.println("Y--" + y);
						}
					}
					else {
						y--;
//						System.out.println("Y--" + y);
					}
				}
				
				else if(x==0) {
//					System.out.println("X==0");
					if(arr[y][x+1] == 1) {
						while(arr[y][x+1] != 0) {
							x++;
//							System.out.println("x++" + x + " " + y);
						}
						if(arr[y][x+1] == 0) y--;
					}
					else {
						y--;
//						System.out.println("Y--" + y);
					}
				}
				
				else if(x==99) {
//					System.out.println("X==99");
					 if(arr[y][x-1] == 1) {
							while(arr[y][x-1] != 0) {
								x--;
//								System.out.println("x--" + x + " " + y);
							}
							if(arr[y][x-1] == 0) y--;
						}
						else {
							y--;
//							System.out.println("Y--" + y);
						}
				}
				
				result = x;
			}
			System.out.printf("#%d %d\n", T, result);
		}
		
		
		
		
	}
	
	

}
