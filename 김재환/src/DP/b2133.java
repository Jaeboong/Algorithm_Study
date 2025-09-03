package DP;

import java.io.*;
import java.util.*;

public class b2133 {

	static int[] memo;	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		memo = new int[N+1];
		
		if(N%2==1) {
			System.out.println(0);
			return;
		}
		
		if(N==2) {
			System.out.println(3);
			return;
		}
		
		if(N==4) {
			System.out.println(11);
			return;
		}
		
		memo[2] = 3;
		memo[4] = 11;
		
		for(int i=6; i<=N; i+=2) {
			f(i);
		}
		
		System.out.println(memo[N]);

	}
	
	private static int f(int n) {
		memo[n] = (memo[n-2] * memo[n-4] + 2)%10007;
		return memo[n];
	}

}
