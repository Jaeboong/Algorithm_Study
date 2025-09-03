import java.io.*;
import java.util.*;

public class b2042 {

	static long[] tree;
	static long[] arr;

	static int N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		arr = new long[N];

		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		Fenwick(N);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				long diff = c - arr[b-1];
				arr[b-1] = c;
				update(b, diff);
			} else {
				sb.append(rangeQuery(b, (int)c)).append("\n");
			}
		}

		System.out.println(sb);
	}

	private static void Fenwick(int size) {
		int n = size;
		tree = new long[n + 1];

		for (int i = 0; i < n; i++) {
			update(i + 1, arr[i]);
		}
	}

	private static void update(int i, long arr2) {
		while (i <= N) {
			tree[i] += arr2;
			i += i & (-i);
		}
	}

	private static long query(int i) {
		long sum = 0;
		while (i > 0) {
			sum += tree[i];
			i -= i & (-i);
		}

		return sum;
	}

	private static long rangeQuery(int left, int right) {
		return query(right) - query(left - 1);
	}

}
