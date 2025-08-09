package backtracking;

import java.util.*;
import java.io.*;

public class b15649 {
	static int n;
	static int m;
	static int[] arr;
	static boolean[] isSel;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int num = 1;
		for(int i=0; i<m; i++) {
			num = num * (n-i);
		}
		
		arr = new int[m];
		isSel = new boolean[n+1];
		
		
		func(0);
		
		//System.out.println(sb);
	}
	
	static void func(int cnt) {

		if(cnt==m) {
			for(int i=0; i<m; i++) sb.append(arr[i]+" ");
			sb.append("\n");
			return;
		}
		
		System.out.println("Search Start: " + cnt);
		
		for(int i=1; i<=n; i++) {
			System.out.println("is " + i + " exist?: ");
			if(isSel[i]) {
				System.out.println("exist");
				continue;
			}
//			System.out.println(cnt);
			arr[cnt] = i;
			isSel[i] = true;
			System.out.println("save " + Arrays.toString(arr));
			func(cnt+1);
			isSel[i] = false;
		}
	}

}
