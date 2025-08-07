package stack;

import java.util.*;
import java.io.*;

public class Main {

		public static void main(String[] args) throws IOException {
			// 입력
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int n = Integer.parseInt(br.readLine());
	        int[] heights = new int[n];

	        StringTokenizer st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < n; i++) {
	            heights[i] = Integer.parseInt(st.nextToken());
	        }

	        int[] result = new int[n];
	        Deque<int[]> stack = new ArrayDeque<>(); // [index, height]
	        
	        for(int i=0; i<n; i++) {
	        	int currHeight = heights[i];
	        	
	        	while(!stack.isEmpty()) {
	        		if(stack.peek()[1] > currHeight) {
	        			result[i] =  stack.peek()[0] + 1;
	        			break;
	        		}
	        		else {
	        			stack.pop();
	        		}
	        	}
	        	
	        	stack.push(new int[] {i, heights[i]});
	        }
	        
	        for(int i=0; i<n; i++) System.out.printf(result[i] + " ");
	    }
}