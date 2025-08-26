package com.kdedevelop.swea.P4012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int cook;
	public void dfsCook(List<Integer> team, int depth, int start, int[] data) {
		if(depth == 2) {
//			System.out.println("TEAM : " + Arrays.toString(team.toArray()));
//			System.out.println("DATA : " + Arrays.toString(data));
			cook += TASTY[data[0]][data[1]] + TASTY[data[1]][data[0]];
		} else {
			for(int i = start + 1 ; i < team.size() ; i ++) {
				data[depth] = team.get(i);
				dfsCook(team, depth + 1, i, data);
			}
		}
	}
	
	int result;
	public void dfsTeam(int depth, int start, int[] data) {
		if(depth == N/2) {
			boolean[] checker = new boolean[N];
			for(int temp : data) checker[temp] = true;
			List<Integer> team1 = new ArrayList<>();
			List<Integer> team2 = new ArrayList<>();
			for(int i = 0 ; i < N ; i ++) {
				if(checker[i]) team1.add(i);
				else team2.add(i);
			}
			
//			System.out.println("TEAM 1 : " + Arrays.toString(team1.toArray()));
//			System.out.println("TEAM 2 : " + Arrays.toString(team2.toArray()));
			
			cook = 0;
			dfsCook(team2, 0, -1, new int[2]);
			int team2Power = cook;
			
			cook = 0;
			dfsCook(team1, 0, -1, new int[2]);
			int team1Power = cook;
			
			int diff = team2Power - team1Power;
			if(diff < 0) diff *= -1;
			
//			System.out.println("TEAM 2 : " + team2Power + " || TEAM 1 : " + team1Power + " || DIFF : " + diff);
			
			result = Math.min(result, diff);
			
		} else {
			for(int i = start + 1 ; i < N ; i ++) {
				data[depth] = i;
				dfsTeam(depth + 1, i, data);
			}
		}
	}
	
	int N;
	int[][] TASTY;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			TASTY = new int[N][N];
			for(int y = 0 ; y < N ; y ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < N ; x ++) {
					TASTY[y][x] = Integer.parseInt(inputLine.nextToken());
				}
			}
			
			dfsTeam(0, -1, new int[N/2]);
			
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
