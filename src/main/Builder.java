package main;

import java.util.ArrayList;
import java.util.Collections;


public class Builder {
	
	private ArrayList<Node> split = new ArrayList<Node>();
	private ArrayList<Node> notSplit = new ArrayList<Node>();

	public Builder() {
	}
	
	public ArrayList<Node> getSplitList(){
		return split;
	}
	
	public ArrayList<Node> getNotSplitList(){
		return notSplit;
	}
	
	public void addNode(Node n){
		notSplit.add(n);
	}

	public Node left(Node node) {
		int zeroIndex = node.findZero();
		Node newNode = new Node();
		newNode.setBoard(node.getBoard());
		newNode.getBoard()[zeroIndex] = newNode.getBoard()[zeroIndex - 1];
		newNode.getBoard()[zeroIndex - 1] = 0;
		newNode.setParent(node);
		newNode.setAction("Left");
		newNode.setPathCost(node.getPathCost() + 1);
		newNode.calcHeuristic();
		return newNode;
	}

	public Node right(Node node) {
		Node newNode = new Node();
		newNode.setBoard(node.getBoard());
		int zeroIndex = newNode.findZero();
		newNode.getBoard()[zeroIndex] = newNode.getBoard()[zeroIndex + 1];
		newNode.getBoard()[zeroIndex + 1] = 0;
		newNode.setParent(node);
		newNode.setAction("Right");
		newNode.setPathCost(node.getPathCost() + 1);
		newNode.calcHeuristic();
		return newNode;
	}

	public Node up(Node node) {
		int zeroIndex = node.findZero();
		Node newNode = new Node();
		newNode.setBoard(node.getBoard());
		newNode.getBoard()[zeroIndex] = newNode.getBoard()[zeroIndex - 3];
		newNode.getBoard()[zeroIndex - 3] = 0;
		newNode.setParent(node);
		newNode.setAction("Up");
		newNode.setPathCost(node.getPathCost() + 1);
		newNode.calcHeuristic();
		return newNode;
	}

	public Node down(Node node) {
		int zeroIndex = node.findZero();
		Node newNode = new Node();
		newNode.setBoard(node.getBoard());
		newNode.getBoard()[zeroIndex] = newNode.getBoard()[zeroIndex + 3];
		newNode.getBoard()[zeroIndex + 3] = 0;
		newNode.setParent(node);
		newNode.setAction("Down");
		newNode.setPathCost(node.getPathCost() + 1);
		newNode.calcHeuristic();
		return newNode;
	}

	@SuppressWarnings("unchecked")
	public void split(Node node) {
		int zeroIndex = node.findZero();
		int[] tempBoard = node.getBoard();

		if (zeroIndex % 3 != 0) {
			notSplit.add(left(node));
			node.setBoard(tempBoard);
		}
		if (zeroIndex % 3 != 2) {
			notSplit.add(right(node));
			node.setBoard(tempBoard);
		}
		if (zeroIndex / 3 != 0) {
			notSplit.add(up(node));
			node.setBoard(tempBoard);
		}
		if (zeroIndex / 3 != 2) {
			notSplit.add(down(node));
			node.setBoard(tempBoard);
		}
		split.add(node);
		notSplit.remove(node);
		Collections.sort(notSplit);
	}
	
	

}
