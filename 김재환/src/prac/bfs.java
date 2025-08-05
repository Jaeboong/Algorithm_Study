package prac;

public class bfs {

	public static void main(String[] args) {
		String names[] = {"차은우", "남주혁", "박효신", "박상진", "정승호", "박재형", "조용준", "김태희", "안효인"};
		int size = names.length;
		CompleteBinaryTree<String> tree = new CompleteBinaryTree<String>(size);
		
		for(String name: names) {
			tree.add(name);
		}
		
		tree.bfs();
	}
}
