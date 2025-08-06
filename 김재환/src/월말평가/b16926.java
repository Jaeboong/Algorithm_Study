package 월말평가;

import java.util.*;
import java.io.*;

public class b16926 {
	static int n, m, r;
	static int[][] arr;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Test4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr= new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rotate();
	}
	
	static void rotate() {
		int layers = Math.min(n, m)/2;
		
		for(int layer =0; layer<layers; layer++) {
			List<Integer> tmp = new ArrayList<>();
			
			
			for(int j=layer; j<m-layer; j++) tmp.add(arr[layer][j]);
			for(int i=layer+1; i<n-layer-1; i++) tmp.add(arr[i][m-layer-1]);
			for(int j=m-layer-1; j>layer; j--) tmp.add(arr[n-layer-1][j]);
			for(int i=n-layer-1; i>layer; i--) tmp.add(arr[i][layer]);

			int len = tmp.size();
			int rot = r%len;
			
			List<Integer> rotate = new ArrayList<>();
			
			for(int i=0; i<len; i++) {
				rotate.add(tmp.get((i+rot)%len));
			}
			
			int idx = 0;
			for(int j=layer; j<m-layer; j++) arr[layer][j]=rotate.get(idx++);
			for(int i=layer+1; i<n-layer-1; i++) arr[i][m-layer-1] = rotate.get(idx++);
			for(int j=m-layer-1; j>layer; j--) arr[n-layer-1][j] = rotate.get(idx++);
			for(int i=n-layer-1; i>layer; i--) arr[i][layer] = rotate.get(idx++);
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int[] row: arr) {
			for(int num : row) {
				sb.append(num).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}

