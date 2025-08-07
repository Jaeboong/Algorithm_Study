package stack;

import java.io.*;
import java.util.*;

public class b1874 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		Stack<Integer> result = new Stack<>();
		Deque<String> print = new LinkedList<>();
		int cnt = 1;
		int idx = 0;
		while (result.size() < n) {
			if (stack.isEmpty() || stack.peek() != arr[idx]) {
				stack.push(cnt++);
				print.offerLast("+");
//				System.out.println(cnt);
				if(cnt > n+1) {
					System.out.println("NO");
					return;
				}
			}
			if (stack.peek() == arr[idx]) {
				result.push(stack.pop());
				idx++;
				print.offerLast("-");
			}

		}
		
//		System.out.println(print);

		if (!print.isEmpty()) {
			while (!print.isEmpty()) {
				System.out.println(print.pollFirst());
			}
		}
	}

}
