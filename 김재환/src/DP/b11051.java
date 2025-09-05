package DP;

import java.io.*;
import java.util.*;

public class b11051 {

	static int N, K;
	static ArrayList<Integer>[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N+2];
		
		for(int i=0; i<=N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<=N+1; i++) {
			f(i);
		}
		
		System.out.println(arr[N+1].get(N-K));
	}
	
	private static void f(int n) {
		if(n==0) {
			arr[n].add(1);
//			System.out.println(arr[n]);
			return;
		}
		if(n==1) {
			arr[n].add(1);
			arr[n].add(1);
//			System.out.println(arr[n]);
			return;
		}
		for(int i=0; i<n; i++) {
			if(i==0 || i==n-1) {
				arr[n].add(1);
			}
			else {
				arr[n].add((arr[n-1].get(i-1) + arr[n-1].get(i))%10007);
			}
		}
//		System.out.println(arr[n]);
	}
	
}
