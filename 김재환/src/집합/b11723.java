package 집합;

import java.io.*;
import java.util.*;

public class b11723 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(br.readLine());
		boolean num[] = new boolean[21];
		
		for(int i=0; i<M; i++) {
			String input[] = br.readLine().split(" ");
			String cmd = input[0];
			if(input.length == 2) {
				int n = Integer.parseInt(input[1]);
				switch(cmd) {
				case "add":
					if(!num[n]) num[n] = true;
					break;
				case "remove":
					if(num[n]) num[n] = false;
					break;
				case "check":
					if(num[n]) sb.append(1).append("\n");
					else sb.append(0).append("\n");;
					break;
				case "toggle":
					if(num[n]) num[n] = false;
					else num[n] = true;
					break;
				}
			}
			if(cmd.equals("all")) {
				for(int x=1; x<21; x++) {
					num[x] = true;
				}
			}
			if(cmd.equals("empty")) {
				for(int x=1; x<21; x++) {
					num[x] = false;
				}
			}
				 
		}
		
		System.out.println(sb);
	}

}
