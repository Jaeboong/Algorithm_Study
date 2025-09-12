import java.io.*;
import java.util.*;

class location {
	int x, y;

	public location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getDistance(int x, int y) {
		return Math.abs(this.x - x) + Math.abs(this.y - y);
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void print() {
		System.out.println("위치: " + "[" + this.x + ", " + this.y + "]");
	}
}

public class b9205 {

	static location[] comb;
	static location cur;
	static location end;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			int n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());

			cur = new location(startX, startY);

			comb = new location[n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				comb[i] = new location(x, y);
			}

			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			end = new location(endX, endY);

			boolean keep = true;

			while (keep) {
				if (reachable(cur, end)) {
//					System.out.println("reachable, break");
					cur = end;
					break;
				}
				keep = findNext();
			}
//			System.out.println(cur.getDistance(endX, endY));
			if (cur.getDistance(endX, endY) == 0) {
				sb.append("happy").append("\n");
			} else {
				sb.append("sad").append("\n");
			}
		}

		System.out.println(sb);
	}

	private static boolean reachable(location a, location b) {
		if (a.getDistance(b.getX(), b.getY()) <= 1000) {
			return true;
		} else
			return false;
	}

	private static boolean findNext() {
		System.out.println("findNext");
		int minX = cur.getX() < end.getX() ? cur.getX() : end.getX();
		int minY = cur.getY() < end.getY() ? cur.getY() : end.getY();
		int maxX = cur.getX() > end.getX() ? cur.getX() : end.getX();
		int maxY = cur.getY() > end.getY() ? cur.getY() : end.getY();
		
		System.out.println("[" + minX + ", " + minY + "] -> [" + maxX + ", " + maxY + "]");

		ArrayList<location> list = new ArrayList<>();
		for (location c : comb) {
			c.print();
			if (minX <= c.getX() && c.getX() <= maxX && minY <= c.getY() && c.getY() <= maxY && cur != c) {
				if (reachable(cur, c)) {
					System.out.println("도달 가능");
					list.add(c);
				}
			}
			if (cur.getDistance(end.getX(), end.getY()) > c.getDistance(end.getX(), end.getY())) {
				if (reachable(cur, c)) {
					System.out.println("도달 가능");
					list.add(c);
				}
			}
		}

		if (list.isEmpty()) {
			return false;
		}

		location tmp = new location(0, 0);
		int min = 9999;

		for (location c : list) {
			int d = cur.getDistance(c.getX(), c.getY());
			if (d < min) {
				min = d;
				tmp = c;
			}
		}

		cur = tmp;

		return true;
	}

}
