package 완전탐색;

import java.io.*;
import java.util.*;

public class b3109_2 {

	static int R, C;
	static char[][] rc;
	static int ans = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		rc = new char[R][C];

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				rc[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(rc[i][j]);
			}
			System.out.println();
		}

		for (int i = 0; i < R; i++) {
			gas(i);
		}

		System.out.println(ans);

	}

	static void gas(int r) {
		int c = 0;
		boolean switch1 = false;
		boolean switch2 = false;
		boolean switch3 = false;
		int pre = 0;
		
		char[][] tmprc = new char[R][C];
		
		for (int i = 0; i < R; i++) {
            // rc의 각 행(1차원 배열)을 통째로 복사해서 tmprc의 각 행에 넣습니다.
            // System.arraycopy()가 더 빠르지만, 이 방법이 더 간결하고 가독성이 좋습니다.
            tmprc[i] = Arrays.copyOf(rc[i], C);
        }
		
		System.out.println();
		System.out.println("rc 출력");
		System.out.println();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(rc[i][j] == 'x') System.out.print("1");
				else System.out.print("0");
			}
			System.out.println();
		}
		
		while (true) {
			
			
			// 탈출 조건 - 맨 오른쪽 도달 시 점수 + 1 후 종료
			System.out.println("현재 r, c 위치: " + r + ", "+ c);
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(tmprc[i][j] == 'x') System.out.print("1");
					else System.out.print("0");
				}
				System.out.println();
			}

			if (c == C - 1) {
				System.out.println("목표지점 도달");
				ans++;
				for (int i = 0; i < R; i++) {
		            // rc의 각 행(1차원 배열)을 통째로 복사해서 tmprc의 각 행에 넣습니다.
		            // System.arraycopy()가 더 빠르지만, 이 방법이 더 간결하고 가독성이 좋습니다.
		            rc[i] = Arrays.copyOf(tmprc[i], C);
		        }
				return;
			}

			//System.out.println("시작위치: " + "r: " + r + " c:" + c);

			if (!switch1 && r > 0 && tmprc[r - 1][c + 1] != 'x') {
				System.out.println("오른쪽 위");
				pre = 1;
				tmprc[r - 1][c + 1] = 'x';
				r--;
				c++;
				switch1 = false;
				switch2 = false;
				switch3 = false;
			} else if (!switch2 && tmprc[r][c + 1] != 'x') {
				System.out.println("오른쪽");
				pre = 2;
				tmprc[r][c + 1] = 'x';
				c++;
				switch1 = false;
				switch2 = false;
				switch3 = false;
			} else if (!switch3 && r < R - 1 && tmprc[r + 1][c + 1] != 'x') {
				System.out.println("오른쪽 아래");
				pre = 3;
				tmprc[r + 1][c + 1] = 'x';
				c++;
				r++;
				switch1 = false;
				switch2 = false;
				switch3 = false;
			} else {
				switch (pre) {
				case 1:
					switch1 = true;
					r++;
					c--;
					System.out.println("switch1: " + switch1 + " r: " + r + " c:" + c);
					break;
				case 2:
					switch2 = true;
					c--;
					System.out.println("switch2: " + switch2 + " r: " + r + " c:" + c);
					break;
				case 3:
					switch3 = true;
					r--;
					c--;
					System.out.println("switch3: " + switch3 + " r: " + r + " c:" + c);
					break;
				default:
					return;
				}
				System.out.println(switch1 + " " + switch2 + " " + switch3);
				if (switch3) {
					System.out.println("갈 수 있는길 없음 탐색 종료");
					return;
				}
			}

		}
	}

}
