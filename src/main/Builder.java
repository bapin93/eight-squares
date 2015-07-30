package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author andres
 *
 */
public class Builder {
	
	private List<Node> _split = new ArrayList<Node>();
	private List<Node> _notSplit = new ArrayList<Node>();

	/**
	 * no-args constructor
	 */
	public Builder() {
	}
	
	/**
	 * @return the list of already split nodes
	 */
	public List<Node> getSplitList(){
		return _split;
	}
	
	/**
	 * @return the list of not yet split nodes
	 */
	public List<Node> getNotSplitList(){
		return _notSplit;
	}
	
	/**
	 * @param node a new node to be added to the not split list
	 */
	public void addNode(final Node node){
		_notSplit.add(node);
	}

	/**
	 * @param node
	 * @return a new node with the open space (0) swapped to the left
	 */
	public Node left(final Node node) {
		int zeroIndex = node.findZero();
		Node newNode = new Node();
		newNode.setBoard(node.getBoard());
		newNode.getBoard()[zeroIndex] = newNode.getBoard()[zeroIndex - 1];
		newNode.getBoard()[zeroIndex - 1] = 0;
		newNode.setParent(node);
		newNode.setAction("Left");
		newNode.setPathCost(node.getPathCost() + 1);
		newNode.calculateHeuristic();
		return newNode;
	}

	/**
	 * @param node
	 * @return a new node with the open space (0) swapped to the right
	 */
	public Node right(final Node node) {
		Node newNode = new Node();
		newNode.setBoard(node.getBoard());
		int zeroIndex = newNode.findZero();
		newNode.getBoard()[zeroIndex] = newNode.getBoard()[zeroIndex + 1];
		newNode.getBoard()[zeroIndex + 1] = 0;
		newNode.setParent(node);
		newNode.setAction("Right");
		newNode.setPathCost(node.getPathCost() + 1);
		newNode.calculateHeuristic();
		return newNode;
	}

	/**
	 * @param node
	 * @return a new node with the open space (0) swapped up
	 */
	public Node up(final Node node) {
		int zeroIndex = node.findZero();
		Node newNode = new Node();
		newNode.setBoard(node.getBoard());
		newNode.getBoard()[zeroIndex] = newNode.getBoard()[zeroIndex - 3];
		newNode.getBoard()[zeroIndex - 3] = 0;
		newNode.setParent(node);
		newNode.setAction("Up");
		newNode.setPathCost(node.getPathCost() + 1);
		newNode.calculateHeuristic();
		return newNode;
	}

	/**
	 * @param node
	 * @return a new node with the open space (0) swapped down
	 */
	public Node down(final Node node) {
		int zeroIndex = node.findZero();
		Node newNode = new Node();
		newNode.setBoard(node.getBoard());
		newNode.getBoard()[zeroIndex] = newNode.getBoard()[zeroIndex + 3];
		newNode.getBoard()[zeroIndex + 3] = 0;
		newNode.setParent(node);
		newNode.setAction("Down");
		newNode.setPathCost(node.getPathCost() + 1);
		newNode.calculateHeuristic();
		return newNode;
	}

	/**
	 * split adds each possible new node to the not split list. Possible nodes 
	 * being nodes created using the left, right, up, and down functions if 
	 * the empty space (0) can be swapped that direction.
	 * @param node
	 */
	@SuppressWarnings("unchecked")
	public void split(final Node node) {
		int zeroIndex = node.findZero();
		int[] tempBoard = node.getBoard();

		if (zeroIndex % 3 != 0) {
			_notSplit.add(left(node));
			node.setBoard(tempBoard);
		}
		if (zeroIndex % 3 != 2) {
			_notSplit.add(right(node));
			node.setBoard(tempBoard);
		}
		if (zeroIndex / 3 != 0) {
			_notSplit.add(up(node));
			node.setBoard(tempBoard);
		}
		if (zeroIndex / 3 != 2) {
			_notSplit.add(down(node));
			node.setBoard(tempBoard);
		}
		_split.add(node);
		_notSplit.remove(node);
		Collections.sort(_notSplit);
	}
}
