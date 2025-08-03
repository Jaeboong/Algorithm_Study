package 월말평가;

import java.util.*;
import java.io.*;

public class Problem3_7월 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int k = Integer.parseInt(br.readLine());
		
		int hmax = 0;
		int wmax = 0;
		int hidx = -1;
		int widx = -1;
		
		int[] dir = new int[6];
		int[] len = new int[6];
		
		for(int i = 0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			dir[i] = Integer.parseInt(st.nextToken());
			len[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i<6; i++) {
			if(dir[i] == 1 || dir[i] == 2) {
				if(len[i] > wmax) {
					wmax = len[i];
					widx = i;
				}
			} else {
				if(len[i] > hmax) {
					hmax = len[i];
					hidx = i;
				}
			}
			
		}
		
		int result = (wmax*hmax)-(len[(widx+3)%6] * len[(hidx+3)%6]);
		System.out.println(result*k);
		
		
		
		
		
		
		
	}
}