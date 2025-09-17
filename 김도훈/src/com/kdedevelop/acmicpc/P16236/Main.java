package com.kdedevelop.acmicpc.P16236;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//상 하 좌 우
//	int[] dirX = {0, 0, -1, 1};
//	int[] dirY = {-1, 1, 0, 0};
	
	//상 좌 하 우
	int[] dirX = {0, -1, 0, 1};
	int[] dirY = {-1, 0, 1, 0};
	
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public int bfs() {
	    Queue<int[]> queue = new LinkedList<>();
	    boolean[][] visit = new boolean[N][N];
	    List<int[]> candidates = new ArrayList<>();
	    
	    queue.offer(SHARK);
	    visit[SHARK[1]][SHARK[0]] = true;
	    
	    int depth = 0;
	    while (!queue.isEmpty()) {
	        int repeat = queue.size();
	        
	        for (int i = 0; i < repeat; i++) {
	            int[] curr = queue.poll();
	            int x = curr[0];
	            int y = curr[1];
	            int size = curr[2];
	            int count = curr[3];
	            
	            // 먹을 수 있는 물고기라면 후보에 추가
	            if (MAP[y][x] > 0 && MAP[y][x] < size) {
	                candidates.add(new int[]{x, y, size, count});
	            }
	            
	            for (int dir = 0; dir < 4; dir++) {
	                int nextX = x + dirX[dir];
	                int nextY = y + dirY[dir];
	                if (isOutOfMap(nextX, nextY)) continue;
	                if (visit[nextY][nextX]) continue;
	                if (MAP[nextY][nextX] > size) continue;
	                
	                queue.offer(new int[]{nextX, nextY, size, count});
	                visit[nextY][nextX] = true;
	            }
	        }
	        
	        // 현재 depth에서 먹을 수 있는 물고기가 있으면 정렬 후 먹음
	        if (!candidates.isEmpty()) {
	            candidates.sort((a, b) -> {
	                if (a[1] == b[1]) return a[0] - b[0]; // y 같으면 x 작은 것
	                return a[1] - b[1]; // y 작은 것 우선
	            });
	            
	            int[] target = candidates.get(0);
	            int size = target[2];
	            int count = target[3] + 1;
	            if (size == count) {
	                count = 0;
	                ++size;
	            }
	            SHARK = new int[]{target[0], target[1], size, count};
	            MAP[target[1]][target[0]] = 0;
	            return depth;
	        }
	        
	        depth++;
	    }
	    
	    return -1;
	}
	
	StringBuilder sb;
	long result;
	int N;
	int[] SHARK;
	int[][] MAP;
	public void solution() throws IOException {
		sb = new StringBuilder();
		result = 0;
		N = Integer.parseInt(br.readLine());
		MAP = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				int vaule = Integer.parseInt(inputLine.nextToken());
				if(vaule == 9) {
					SHARK = new int[] {j, i, 2, 0};
					vaule = 0;
				}
				MAP[i][j] = vaule;
			}
		}
		
//		System.out.println("N : " + N);
		
		while(true) {
			int move = bfs();
			
//			System.out.println("MOVE : " + move);
//			System.out.println("SHARK X : " + SHARK[0] + ", Y : " + SHARK[1] + ", SIZE : " + SHARK[2] + ", COUNT : " + SHARK[3]);
//			String temp = br.readLine();
			
			if(move == -1) break;
			
			result += move;
		}
		
		sb.append(String.valueOf(result));
		
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
