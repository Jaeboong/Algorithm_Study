package 분할정복;

import java.util.*;
import java.io.*;

public class b10816 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		HashMap<Integer, Integer> Nmap = new HashMap<>(N);
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			Nmap.put(i, Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int[] Marr = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			Marr[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Map.Entry<Integer, Integer>> nList  = new ArrayList<>(Nmap.entrySet());
		nList.sort(Comparator.comparing(Map.Entry::getValue));
		
	}

}
