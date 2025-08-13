package com.kdedevelop.acmicpc.P1991;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Node {
	public Node(char name) {
		this.name = name;
	}
	char name;
	Node leftNode;
	Node rightNode;
}

public class Main {
	static BufferedWriter bw;
	static void pre(Node node) throws IOException {
		if (node == null) return;
		bw.write(node.name);
		pre(node.leftNode);
		pre(node.rightNode);
	}
	
	static void in(Node node) throws IOException {
		if (node == null) return;
		in(node.leftNode);
		bw.write(node.name);
		in(node.rightNode);
	}
	
	static void post(Node node) throws IOException {
		if (node == null) return;
		post(node.leftNode);
		post(node.rightNode);
		bw.write(node.name);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N];
		for (int i = 0 ; i < N ; i ++) {
			nodes[i] = new Node((char) (i + 'A'));
		}
		
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer edge = new StringTokenizer(br.readLine());
			int parent = edge.nextToken().charAt(0);
			int left = edge.nextToken().charAt(0);
			int right = edge.nextToken().charAt(0);
			
			if (left != '.') nodes[parent - 'A'].leftNode = nodes[left - 'A'];
			if (right != '.') nodes[parent - 'A'].rightNode = nodes[right - 'A'];
		}
		
		pre(nodes[0]);
		bw.write("\n");
		in(nodes[0]);
		bw.write("\n");
		post(nodes[0]);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
