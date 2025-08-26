package com.kdedevelop.swea.P4130;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dir = {-1, 1, -1, 1};
	int[][] gearConnectIndex = {
			{6, 2},
			{},
			{},
			{}
	};
	
	public int modularCursor(int cursor, int a) {
		return ((cursor + a) + 8) % 8;
	}
	
	int K;
	boolean[][] GEAR;
	int[] gearCursor;
	int[][] ROTATEs;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			GEAR = new boolean[4][8];
			K = Integer.parseInt(br.readLine());
			gearCursor = new int[4];
			ROTATEs = new int[K][2];
			for(int i = 0 ; i < 4 ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 8 ; j ++) {
					GEAR[i][j] = inputLine.nextToken().charAt(0) == '1';
				}
			}
			for(int i = 0 ; i < K ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				int gear = Integer.parseInt(inputLine.nextToken()) - 1;
				int dir = Integer.parseInt(inputLine.nextToken());
				int[] rotate = {gear, dir};
				ROTATEs[i] = rotate;
			}
			
			for(int i = 0 ; i < K ; i ++) {
				int[] rotateInfo = ROTATEs[i];
				
				int gearNumber = rotateInfo[0];
				
				boolean[][] rotate = new boolean[4][2];
				rotate[gearNumber][0] = true;
				rotate[gearNumber][1] = rotateInfo[1] != 1;
				
				for(int gear = gearNumber ; gear > 0 ; gear --) {
//					System.out.println("GEAR : " + gear);
					if(!rotate[gear][0]) break;
					
					int leftGear = gear - 1;
					
					int leftGearRight = modularCursor(gearCursor[leftGear], 2);
					int currentGearLeft = modularCursor(gearCursor[gear], 6);
					
//					System.out.println("LEFT GEAR RIGHT : " + leftGearRight + " : " + GEAR[leftGear][leftGearRight] + " || CURR GEAR LEFT : " + currentGearLeft + " : " +  GEAR[gear][currentGearLeft]);
					
					if(GEAR[leftGear][leftGearRight] != GEAR[gear][currentGearLeft]) {
						rotate[leftGear][0] = true;
						rotate[leftGear][1] = !rotate[gear][1];
					}
				}
				
				for(int gear = gearNumber ; gear < 3 ; gear ++) {
//					System.out.println("GEAR : " + gear);
					if(!rotate[gear][0]) break;
					
					int rightGear = gear + 1;
					
					int currentGearRight = modularCursor(gearCursor[gear], 2);
					int rightGearLeft = modularCursor(gearCursor[rightGear], 6);
					
//					System.out.println("CURR GEAR RIGHT : " + currentGearRight + " : " +  GEAR[gear][currentGearRight] + " || RIGHT GEAR LEFT : " + rightGearLeft + " : " + GEAR[rightGear][rightGearLeft]);
					
					if(GEAR[gear][currentGearRight] != GEAR[rightGear][rightGearLeft]) {
						rotate[rightGear][0] = true;
						rotate[rightGear][1] = !rotate[gear][1];
					}
				}
				
				for(int gear = 0 ; gear < 4 ; gear ++) {
					if(rotate[gear][0]) gearCursor[gear] = modularCursor(gearCursor[gear], rotate[gear][1] ? 1 : -1);
				}
				
//				System.out.println(Arrays.toString(gearCursor));
			}
			
			int result = 0;
			for(int i = 0 ; i < 4 ; i ++) {
//				System.out.println(GEAR[i][gearCursor[i]]);
				if(GEAR[i][gearCursor[i]]) {
					result += (int) Math.pow(2, i);
				}
			}
			
//			System.out.println(result);
			bw.write("#" + (testCase + 1) + " " + result + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
