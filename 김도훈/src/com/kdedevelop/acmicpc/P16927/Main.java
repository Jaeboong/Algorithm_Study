package com.kdedevelop.acmicpc.P16927;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer inputNMR = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(inputNMR.nextToken());
		int M = Integer.parseInt(inputNMR.nextToken());
		int R = Integer.parseInt(inputNMR.nextToken());
		
		int[][] matrix = new int[N][M];
		
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				matrix[i][j] = Integer.parseInt(inputLine.nextToken());
			}
		}
		
		int top = 0;
		int right = M-1;
		int bottom = N-1;
		int left = 0;
		
		for (int i = 0 ; i < Math.min(M, N) / 2 ; i ++) {
			Queue<Integer> queue = new LinkedList<>();
			for (int j = left ; j <= right -1 ; j ++) queue.add(matrix[top][j]);
			for (int j = top ; j <= bottom - 1 ; j ++) queue.add(matrix[j][right]);
			for (int j = right ; j >= left + 1 ; j --) queue.add(matrix[bottom][j]);
			for (int j = bottom ; j >= top + 1 ; j --) queue.add(matrix[j][left]);
			
			for (int rotate = 0 ; rotate < R % queue.size() ; rotate ++) queue.add(queue.poll());
			
			for (int j = left ; j <= right -1 ; j ++) matrix[top][j] = queue.poll();
			for (int j = top ; j <= bottom-1 ; j ++) matrix[j][right] = queue.poll();
			for (int j = right ; j >= left + 1 ; j --) matrix[bottom][j] = queue.poll();
			for (int j = bottom ; j >= top + 1 ; j --) matrix[j][left] = queue.poll();
			
			top ++;
			right --;
			bottom --;
			left ++;
		}
		
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				bw.write(matrix[i][j] + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
