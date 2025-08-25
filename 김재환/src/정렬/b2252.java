package 정렬;
import java.util.*;
import java.io.*;
public class b2252 {

	static int N, M;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//필요한 배열 선언 후 배열 초기화
		Queue<Integer> q = new ArrayDeque<>();
		ArrayList<Integer>[] list;
		list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList();
		}
		
		int from, to;
		int[] inD = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			//from to입력받은 후 list에 각 관계들 저장. inD에 to들 ++
			list[from].add(to);
			inD[to]++;
		}
		
		//입력차수가 0인애들 시작 q에 저장
		for(int i=1; i<=N; i++) {
			if(inD[i] == 0) {
				q.offer(i);
			}
		}
		
//		System.out.println(Arrays.toString(list));
		
		int idx;
		List<Integer> result = new ArrayList<>();
		while(!q.isEmpty()) {
			//idx에 q에 있는 거 1개 poll
			idx = q.poll();
			//결과에 idx 추가
			result.add(idx);
			for(int i : list[idx]) {
				//list 뒤져서 idx 뒤에 서야 하는 애들 i로 돌리기
				inD[i]--; //입력차수 1개 감소
				if(inD[i] == 0) {	//만약 입력차수 0이면 q에 추가
					q.offer(i);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int ans: result) {
			sb.append(ans + " ");
		}
		
		System.out.println(sb);
	}

}
