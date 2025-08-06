package prac;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<T> {

	private Object[] nodes;
	private int lastIndex;
	private final int SIZE;

	public CompleteBinaryTree(int size) {
		super();
		SIZE = size;
		nodes = new Object[size + 1];
	}
	
	public boolean isEmpty() {
		return lastIndex == 0;
	}
	
	public boolean isFull() {
		return lastIndex == SIZE;
	}
	
	public void add(T e) {
		if(isFull()) throw new RuntimeException("완전이진 트리가 아닙니다.");
		
		nodes[++lastIndex] = e;
	}
	
	public void bfs() {
		//탐색 순서 관리할 큐 준비
		Queue<Integer> queue = new ArrayDeque<Integer>();
		//큐 탐색 시작 정보 넣기
		queue.offer(1);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll(); //탐색 대상 중 맨 앞 정보 꺼내기
			//탐색시 해야하는 로직
			System.out.println(nodes[cur]);
			if(cur*2<=lastIndex) queue.offer(cur*2);
			if(cur*2+1<=lastIndex) queue.offer(cur*2+1);
			
			
		}
	}
}