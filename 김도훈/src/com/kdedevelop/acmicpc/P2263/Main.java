package com.kdedevelop.acmicpc.P2263;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Node {
	public Node(int data) {
		this.data = data;
	}
	int data;
	Node leftNode;
	Node rightNode;
}

public class Main {
	public static BufferedWriter bw;
	
	public static void print(Node node) throws IOException {
		if (node == null) return;
		
		bw.write(String.valueOf(node.data) + " ");
		print(node.leftNode);
		print(node.rightNode);
	}
	
	public static Node createNode(int[] inOrder, int inOrderStart, int inOrderEnd, int[] postOrder, int postOrderStart, int postOrderEnd, Map<Integer, Integer> index) {
		if (inOrderStart > inOrderEnd) {
			return null;
		} else {
			Node node = new Node(postOrder[postOrderEnd]);
			int nodeData = node.data;
			int nodeIndex = index.get(nodeData);
			
			int leftInOrderStart = inOrderStart;
			int leftInOrderEnd = nodeIndex-1;
			int leftInOrderSize = leftInOrderEnd - leftInOrderStart;
			int leftPostOrderStart = postOrderStart;
			int leftPostOrderEnd = leftPostOrderStart + leftInOrderSize;
			node.leftNode = createNode(inOrder, leftInOrderStart, leftInOrderEnd, postOrder, leftPostOrderStart, leftPostOrderEnd, index);
			
			int rightInOrderStart = nodeIndex + 1;
			int rightInOrderEnd = inOrderEnd;
			int rightInOrderSize = rightInOrderEnd - rightInOrderStart;
			int rightPostOrderEnd = postOrderEnd - 1;
			int rightPostOrderStart = rightPostOrderEnd - rightInOrderSize;
			node.rightNode = createNode(inOrder, rightInOrderStart, rightInOrderEnd, postOrder, rightPostOrderStart, rightPostOrderEnd, index);
			
			return node;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> index = new HashMap<>();
		int[] inOrder = new int[N];
		int[] postOrder = new int[N];
		
		StringTokenizer inputInOrder = new StringTokenizer(br.readLine());
		StringTokenizer inputPostOrder = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i ++) {
			inOrder[i] = Integer.parseInt(inputInOrder.nextToken());
			index.put(inOrder[i], i);
			postOrder[i] = Integer.parseInt(inputPostOrder.nextToken());
		}
		
//		for (int key : index.keySet()) {
//			System.out.println("DATA : " + key + " || INDEX : " + index.get(key));
//		}
		
		Node root = createNode(inOrder, 0, N-1, postOrder, 0, N-1, index);
		print(root);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
