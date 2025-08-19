package com.kdedevelop.swea.P4796;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.crypto.spec.RC2ParameterSpec;

public class Solution {
//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner scanner = new Scanner(System.in);
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int result;
	int N;
	int[] heights;
	void solution() throws IOException {
//		int TotalTestCase = Integer.parseInt(br.readLine());
		int TotalTestCase = scanner.nextInt();
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
//			System.out.println("TEST CASE : " + testCase);
			result = 0;
			
//			N = Integer.parseInt(br.readLine());
			N = scanner.nextInt();
			heights = new int[N];
//			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			
			List<Integer> top = new LinkedList<>();
			heights[0] = scanner.nextInt();
			heights[1] = scanner.nextInt();
			for (int i = 2 ; i < N ; i ++) {
//				int height = Integer.parseInt(lineInput.nextToken());
				int height = scanner.nextInt();
				heights[i] = height;
				if (heights[i-2] < heights[i-1] && heights[i-1] > heights[i]) top.add((i-1));
			}
			
			for (int index : top) {
//				System.out.println("INDEX : " + index);
				int leftCounter = 0;
				for (int i = index ; i > 0 ; i --) {
					if (heights[i] > heights[i-1]) {
						leftCounter ++;
					} else {
						break;
					}
				}
				
				int rightCounter = 0;
				for (int i = index ; i < N-1 ; i ++) {
					if (heights[i] > heights[i+1]) {
						rightCounter ++;
					} else {
						break;
					}
				}
//				System.out.println("LEFT COUNTER : " + leftCounter + " || RIGHT COUNTER : " + rightCounter);
				result += leftCounter * rightCounter;
			}
				
//				System.out.println("LEFT POINT : " + leftPoint + " || RIGHT POINT : " + rightPoint);
//				System.out.println("START POINT : " + startPoint + " || LAST POINT : " + lastPoint);
//				System.out.println("LEFT COUNTER : " + leftCounter + " || RIGHT COUNTER : " + rightCounter);
				
//			System.out.println("==========================");
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
//		br.close();
		scanner.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
