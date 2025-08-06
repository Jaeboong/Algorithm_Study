package 월말평가;

import java.io.*;
import java.util.*;

public class TEST2_7월 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Test2.txt"));;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int cnt = 1;
		int[] result = new int[T];
		
		while (cnt <= T) {
			
			int N = Integer.parseInt(br.readLine());
			int[][] NN = new int[N][N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] power = new int[n];
			for (int i = 0; i < n; i++) {
				power[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			
			int max = 0;
			
			for(int i=0; i<n; i++) {
				int y = Integer.parseInt(st.nextToken())-1;
				int x = Integer.parseInt(st.nextToken())-1;
				NN[y][x]++;
				for(int k=1; k<=power[i]; k++) {
					if(y+k<N)NN[y+k][x]++;
					if(y-k>=0)NN[y-k][x]++;
					if(x+k<N)NN[y][x+k]++;
					if(x-k>=0)NN[y][x-k]++;
					if(y+k<N&&x+k<N)NN[y+k][x+k]++;
					if(y-k>=0&&x-k>=0)NN[y-k][x-k]++;
					if(y+k<N&&x-k>=0)NN[y+k][x-k]++;
					if(y-k>=0&&x+k<N)NN[y-k][x+k]++;
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(max<NN[i][j]) max = NN[i][j];
				}
			result[cnt-1] = max;
			}
			
			cnt++;
		}
		
		for(int i=0; i<T; i++) {
			System.out.printf("#%d %d\n", i+1, result[i]);
		}

	}

}
