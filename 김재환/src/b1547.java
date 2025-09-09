import java.io.*;
import java.util.*;

public class b1547 {

	static int cup[] = { 0, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			swap(a, b);
		}

		for (int i = 1; i < 4; i++) {
			if (cup[i] == 1) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println(-1);
	}

	private static void swap(int a, int b) {
		int tmp = cup[a];
		cup[a] = cup[b];
		cup[b] = tmp;
	}

}
