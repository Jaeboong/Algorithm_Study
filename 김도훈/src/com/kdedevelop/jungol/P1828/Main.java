package com.kdedevelop.jungol.P1828;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
class Option {
	public Option(String input) {
		StringTokenizer st = new StringTokenizer(input);
		this.min = Integer.parseInt(st.nextToken());
		this.max = Integer.parseInt(st.nextToken());
	}
	public Option(int min, int max) {
		this.min = min;
		this.max = max;
	}
	int min;
	int max;
}
public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int N;
	Option[] options;
	Map<Integer, Map<Integer, Option>> optionMap;
	void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		options = new Option[N];
		optionMap = new TreeMap<>();
		for (int i = 0 ; i < N ; i ++) {
			Option option = new Option(br.readLine());
			options[i] = option;
			if (!optionMap.containsKey(option.min)) optionMap.put(option.min, new TreeMap<>());
			optionMap.get(option.min).put(option.max, option);
		}
		
		int result = 1;
		int TOTAL_MAX = Integer.MAX_VALUE;
		for (int min : optionMap.keySet()) {
			Map<Integer, Option> minMap = optionMap.get(min);
			Option option = minMap.get(minMap.keySet().iterator().next());
//			System.out.println("TOTAL MAX : " + String.format("%10d", TOTAL_MAX) + " || MIN : " + String.format("%10d", option.min) + " || MAX : " + String.format("%10d", option.max) + " || SIZE : " + result);
			if (TOTAL_MAX >= min) {
				TOTAL_MAX = Math.min(TOTAL_MAX, option.max);
			} else {
				TOTAL_MAX = option.max;
				result ++;
			}
//			System.out.println("TOTAL MAX : " + String.format("%10d", TOTAL_MAX) + " || MIN : " + String.format("%10d", option.min) + " || MAX : " + String.format("%10d", option.max) + " || SIZE : " + result);
//			System.out.println("============================================================================================================");
		}
		
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
