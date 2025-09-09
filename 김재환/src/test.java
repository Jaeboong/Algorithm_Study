import java.io.*;
import java.util.*;

public class test {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int S = Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(S == a) {
				S = b;
				continue;
			}
			if(S == b) {
				S = a;
				continue;
			}
		}
		
		System.out.println(S);
	}
}
