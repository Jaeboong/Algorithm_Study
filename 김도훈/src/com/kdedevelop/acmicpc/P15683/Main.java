package com.kdedevelop.acmicpc.P15683;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//상0 하1 좌2 우3
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	int[][] camera1VisionDir = {{0}, {1}, {2}, {3}};
	int[][] camera2VisionDir = {{0, 1}, {2, 3}};
	int[][] camera3VisionDir = {{0, 3}, {1, 3}, {1, 2}, {0, 2}};
	int[][] camera4VisionDir = {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}};
	int[][] camera5VisionDir = {{0, 1, 2, 3}};
	int[][][] cameraVisionDir = {camera1VisionDir, camera2VisionDir, camera3VisionDir, camera4VisionDir, camera5VisionDir};
	
	public boolean isOutOfOffice(int x, int y) {
		if(x < 0 || x >= M) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	int result;
	public void function(int depth, boolean[][][] cameraVision) {
		if(depth == CAMERA_COUNT) {
			int counter = SPACE_COUNT;
			for(int y = 0 ; y < N ; y ++) {
				for(int x = 0 ; x < M ; x ++) {
					if(OFFICE[y][x] != 0) continue;
					for(boolean[][] vision : cameraVision) {
						if(vision[y][x]) {
							-- counter;
							break;
						}
					}
				}
			}
//			if (result > counter) {
////				System.out.println("");
////				for(boolean[][] vision : cameraVision) {
////					for(int tmep1 = 0 ; tmep1 < vision.length ; tmep1 ++) {
////						for(int tmep2 = 0 ; tmep2 < vision[tmep1].length ; tmep2 ++) {
////							System.out.print((vision[tmep1][tmep2] ? "1" : "0") + " ");
////						}
////						System.out.println("");
////					}
////					System.out.println("");
////				}
//			}
			result = Math.min(result, counter);
		} else {
			int[] camera = CAMERA.get(depth);
			int type = camera[0];
			int x = camera[1];
			int y = camera[2];
			
			for(int[] cameraDir : cameraVisionDir[type - 1]) {
//				System.out.println(Arrays.toString(cameraDir) + " is VISION");
				boolean[][] vision = new boolean[N][M];
				for(int visionDir : cameraDir) {
					int nextX = x + dirX[visionDir];
					int nextY = y + dirY[visionDir];
					while(true) {
						if(isOutOfOffice(nextX, nextY)) break;
						if(OFFICE[nextY][nextX] == 6) break;
						vision[nextY][nextX] = true;
						nextX = nextX + dirX[visionDir];
						nextY = nextY + dirY[visionDir];
					}
				}
				cameraVision[depth] = vision;
				function(depth + 1, cameraVision);
			}
		}
	}
	
	int N;
	int M;
	int[][] OFFICE;
	List<int[]> CAMERA;
	int CAMERA_COUNT;
	int SPACE_COUNT;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		OFFICE = new int[N][M];
		CAMERA = new ArrayList<>();
		SPACE_COUNT = 0;
		result = Integer.MAX_VALUE;
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for(int x= 0 ; x < M ; x ++) {
				int temp = Integer.parseInt(inputLine.nextToken());
				if(temp >= 1 && temp <= 5) CAMERA.add(new int[] {temp, x, y});
				if(temp == 0) ++ SPACE_COUNT;
				OFFICE[y][x] = temp;
			}
		}
		CAMERA_COUNT = CAMERA.size();
		
//		System.out.println(" ");
//		for(int[][] camera : cameraVisionDir) {
//			for(int[] cameraDir : camera) {
//				for(int visionDir : cameraDir) {
//					System.out.print(visionDir + " ");
//				}
//				System.out.println(" ");
//			}
//			System.out.println(" ");
//		}
//		System.out.println(" ");
		
		function(0, new boolean[8][N][M]);
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
