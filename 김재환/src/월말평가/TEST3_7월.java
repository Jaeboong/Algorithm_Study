package 월말평가;

import java.util.*;
import java.io.*;

public class TEST3_7월 {

	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("Test3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		int[][] arr = new int[6][2];
		
		int d=0;
		int h=0;
		int pre1=999;
		int pre2=0;
		int square=0;
		
		for(int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			int x = arr[i][0]-pre1;
//			System.out.println(x);
			if((arr[i][0]==4 || arr[i][0]==3) && h<arr[i][1]) h=arr[i][1];
			if((arr[i][0]==2 || arr[i][0]==1) && d<arr[i][1]) d=arr[i][1];
			if((x==2||x==-3||x==-1)) {square = pre2*arr[i][1];
//			System.out.println("fine turn");
			}
			else {
				pre2=arr[i][1];
				pre1=arr[i][0];
			}
			if(i==5 && square==0) {
				square = arr[i][1] * arr[0][1];
				
			}
		}
		
//		System.out.println("BigSqaure: " + h*d + " SmallSqaure: " + square);
		
		
		
		System.out.println(n*((h*d) - square));
	}

}
