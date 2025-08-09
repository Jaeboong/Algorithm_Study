package backtracking;

import java.util.*;
import java.io.*;

public class b15650 {
	
	static int n, m;
	static int arr[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		
		func(0, 1);
		System.out.println(sb);
	}
	
	static void func(int cnt, int s) {
		if(cnt == m) {
			for(int a: arr) sb.append(a +" ");
			sb.append("\n");
			return;
		}
//		System.out.println("Search Start: " + cnt);
		for(int i=s; i<=n; i++) {
//			System.out.println("is " + i + " exist?: ");
			arr[cnt] = i;
//			System.out.println("save " + Arrays.toString(arr));
			func(cnt+1, i+1);
		}
	}

}
