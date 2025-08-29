package com.kdedevelop.swea.P5658;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int convert(char value) {
		switch(value) {
			case '0' : return 0;
			case '1' : return 1;
			case '2' : return 2;
			case '3' : return 3;
			case '4' : return 4;
			case '5' : return 5;
			case '6' : return 6;
			case '7' : return 7;
			case '8' : return 8;
			case '9' : return 9;
			case 'A' : return 10;
			case 'B' : return 11;
			case 'C' : return 12;
			case 'D' : return 13;
			case 'E' : return 14;
			case 'F' : return 15;
		}
		throw new RuntimeException(value + " IS NOT HEXA");
	}
	
	public int modularCursor(int cursor, int value) {
		return ((cursor + N) + value) % N;
	}
	
	public char[][] getNumbers(int cursor) {
		char[][] number = new char[4][length];
		for(int j = 0 ; j < 4 ; j ++) {
			for(int k = 0 ; k < length ; k ++) {
				number[j][k] = NUMBERs.charAt(modularCursor(cursor, (j * length) + k));
			}
		}
		return number;
	}
	
	public int[] numbers(String hexaNum) {
		int[] result = new int[hexaNum.length()];
		for(int i = 0 ; i < hexaNum.length() ; i ++) {
			char chr = hexaNum.charAt(i);
			int num = convert(chr);
			result[i] = num;
		}
		return result;
	}
	
	public long getSum(int[] numbers) {
		long num = 0;
		long pow = 1;
		for(int j = length - 1 ; j >= 0 ; j --) {
			long value = numbers[j] * pow;
			pow *= 16;
			num += value;
		}
		return num;
	}
	
	public String convert(char[] nums) {
		return String.valueOf(nums);
	}
	
	public Long[] func() {
		Set<String> set = new TreeSet<>();
		
		int cursor = 0;
		for(int rotate = 0 ; rotate < length ; rotate ++) {
			char[][] numbers = getNumbers(cursor);
			
			for(char[] nums : numbers) {
				set.add(convert(nums));
			}
			
//			for(char[] nums : numbers) {
//				System.out.print(convert(nums) + " ");
//			}
//			System.out.println("");
			
			cursor = modularCursor(cursor, 1);
		}
		
		Long[] result = new Long[set.size()];
		int resultPoint = 0;
		for(String hexaNum : set) {
			int[] nums = numbers(hexaNum);
			long sum = getSum(nums);
			result[resultPoint ++] = sum;
		}
		Arrays.sort(result, Collections.reverseOrder());
		
		return result;
	}
	
	long result;
	int N;
	int K;
	String NUMBERs;
	int length;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			
			StringTokenizer inputNK = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNK.nextToken());
			K = Integer.parseInt(inputNK.nextToken());
			length = N / 4;
			NUMBERs = br.readLine();
			
			Long[] sumArray = func();
			result = sumArray[K - 1];
			
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
