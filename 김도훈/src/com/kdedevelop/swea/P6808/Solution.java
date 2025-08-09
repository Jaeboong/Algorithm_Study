package com.kdedevelop.swea.P6808;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	
	static int winGyu;
	static int winIn;
	public static void counter(List<Integer> cardsGyu, List<Integer> cardsIn, boolean[] visited, int depth, int[] data) {
		if (depth == 9) {
			int pointGyu = 0;
			int pointIn = 0;
			
			for (int i = 0 ; i < 9 ; i ++) {
				int cardGyu = cardsGyu.get(i);
				int cardIn = cardsIn.get(data[i]);
				
				if (cardGyu > cardIn) pointGyu += (cardGyu + cardIn);
				if (cardGyu < cardIn) pointIn += (cardGyu + cardIn);
			}
			
			if (pointGyu > pointIn) winGyu ++;
			if (pointGyu < pointIn) winIn ++;
		} else {
			for (int i = 0 ; i < 9 ; i ++) {
				if (visited[i]) continue;
				visited[i] = true;
				data[depth] = i;
				counter(cardsGyu, cardsIn, visited, depth+1, data);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			winGyu = 0;
			winIn = 0;
			
			Set<Integer> totalCards = new TreeSet<>();
			for (int i = 1 ; i <= 18 ; i ++) totalCards.add(i);
			List<Integer> cardsGyu = new ArrayList<>(9);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int cardCounter = 0 ; cardCounter < 9 ; cardCounter ++) {
				int card = Integer.parseInt(st.nextToken());
				totalCards.remove(card);
				cardsGyu.add(card);
			}
			List<Integer> cardsIn = new ArrayList<>(totalCards);
			
			counter(cardsGyu, cardsIn, new boolean[9], 0, new int[9]);
			
			bw.write("#" + (testCase+1) + " " + winGyu + " " + winIn + "\n");
		
			/*
			System.out.println("");
			for (int card : cardsIn) {
				System.out.print(card + ", ");
			}
			System.out.println("");
			for (int card : cardsGyu) {
				System.out.print(card + ", ");
			}
			System.out.println("");
			System.out.println("");
			*/
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
