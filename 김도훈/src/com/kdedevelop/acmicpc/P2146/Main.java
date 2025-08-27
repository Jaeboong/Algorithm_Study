package com.kdedevelop.acmicpc.P2146;

import java.io.*;
import java.util.*;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] dirX = {0, 0, -1, 1};
    int[] dirY = {-1, 1, 0, 0};

    public boolean isOutOfMap(int x, int y) {
        if(x < 0 || x >= N) return true;
        if(y < 0 || y >= N) return true;
        return false;
    }
    
    public void printMap() {
      System.out.println("");
      for(int i = 0 ; i < N ; i ++) {
          for(int j = 0 ; j < N ; j ++) {
			  System.out.print(String.format("%2d", islandGroupMap[i][j]));
          }
          System.out.println("");
      }
      System.out.println("");
    }
    
    public Queue<int[]> islandGroup(int x, int y, int groupId) {
    	Queue<int[]> nearSea = new LinkedList<>();
    	Queue<int[]> queue = new LinkedList<>();
    	
    	queue.add(new int[] {x, y});
    	visit[y][x] = true;
    	
    	while(true) {
    		if(queue.isEmpty()) break;
    		
    		int[] xy = queue.poll();
    		islandGroupMap[xy[1]][xy[0]] = groupId;
    		
    		for(int dir = 0 ; dir < 4 ; dir ++) {
    			int nextX = xy[0] + dirX[dir];
    			int nextY = xy[1] + dirY[dir];
    			
    			if(isOutOfMap(nextX, nextY)) continue;
    			if(visit[nextY][nextX]) continue;
    			
    			if(MAP[nextY][nextX]) queue.add(new int[] {nextX, nextY});
    			else                  nearSea.add(new int[] {nextX, nextY, 1, groupId});
    			
    			visit[nextY][nextX] = true;
    		}
    	}
    	
    	return nearSea;
    }

    int N;
    boolean[][] MAP;
    int islandGroupCount = 0;
    int[][] islandGroupMap;
    boolean[][] visit;
    int result;
    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());
        MAP = new boolean[N][N];
        islandGroupMap = new int[N][N];
        visit = new boolean[N][N];
        result = Integer.MAX_VALUE;
        for(int i = 0 ; i < N ; i ++) {
            StringTokenizer inputLine = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++) {
                boolean island = inputLine.nextToken().charAt(0) == '1';
                MAP[i][j] = island;
            }
        }
        
        Map<Integer, Queue<int[]>> nearSeaGroup = new HashMap<>();
        for(int y = 0 ; y < N ; y ++) {
        	for(int x = 0 ; x < N ; x ++) {
        		if(!MAP[y][x]) continue;
        		if(visit[y][x]) continue;
        		Queue<int[]> nearSea = islandGroup(x, y, ++ islandGroupCount);
        		nearSeaGroup.put(islandGroupCount, nearSea);
        	}
        }
        
        for(int groupId : nearSeaGroup.keySet()) {
        	Queue<int[]> nearSea = nearSeaGroup.get(groupId);
        	visit = new boolean[N][N];
        	while(true) {
        		if(nearSea.isEmpty()) break;
        		
        		int[] curr = nearSea.poll();
        		int x = curr[0];
        		int y = curr[1];
        		int depth = curr[2];
        		int startIslandId = curr[3];
        		if(MAP[y][x] && islandGroupMap[y][x] != startIslandId) {
        			result = Math.min(result, depth);
        		}
        		
        		visit[curr[1]][curr[0]] = true;
        		
        		for(int dir = 0 ; dir < 4 ; dir ++) {
        			int nextX = x + dirX[dir];
        			int nextY = y + dirY[dir];
        			
        			if(isOutOfMap(nextX, nextY)) continue;
        			if(visit[nextY][nextX]) continue;
        			
        			nearSea.add(new int[] {nextX, nextY, depth + 1, startIslandId});
        			visit[nextY][nextX] = true;
        		}
        	}
        }
        
        bw.write(String.valueOf(result - 1));

        br.close();
        bw.flush();
        bw.close();
    }
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
