package main;

import java.util.Random;

@SuppressWarnings("rawtypes")
public class Node implements Comparable{

	private int[] board = new int[9];
	private int[] solution = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
	private Node parent;
	private String action;
	private int pathCost, heuristic;

	public Node() {
		heuristic = 0;
		pathCost = 0;
		parent = null;
		action = "NULL";
		
		Random random = new Random();
		String temp = "";
		for (int x = 0; x < board.length; x++) {
			int num = random.nextInt((8 - 0) + 1);

			while (temp.contains(num + "")) {
				num = random.nextInt((8 - 0) + 1);
			}
			board[x] = num;
			temp += "" + num;
		}
		calcHeuristic();
	}
	
	public int compareTo(Object n) {
		Node n1 = (Node)n;
		int compareHeu = (int)(n1.getHeuristic());
		return (int)this.heuristic - compareHeu;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public String getAction(){
		return action;
	}
	
	public int getPathCost(){
		return pathCost;
	}
	
	public int[] getBoard() {
		return board;
	}
	
	public int[] getSolution(){
		return solution;
	}
	
	public int getHeuristic(){
		return heuristic;
	}
	
	public void setAction(String a){
		action = a;
	}
	
	public void setParent(Node p){
		parent = p;
	}
	
	public void setPathCost(int cost){
		pathCost = cost;
	}
	
	public void setHeuristic(int newH){
		heuristic = newH;
	}
	
	public void setBoard(int[] newBoard){
		for(int i = 0; i < board.length; i++){
			board[i] = newBoard[i];
		}
	}
	
	public int findZero() {
		int index = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0) {
				index = i;
			}
		}
		return index;
	}
	
	public void calcHeuristic(){
		int value = 0;
		for(int i = 0; i < board.length; i++){
			if(getBoard()[i] != solution[i])
				value++;
		}
	    value += getPathCost();
		setHeuristic(value);
	}
	
	public boolean isSolvable() {
	    int inversions = 0; 
	    for(int i = 0; i < board.length - 1; i++) {
	      for(int j = i + 1; j < board.length; j++)
	        if(board[i] > board[j]) 
	        	inversions++;
	      if(board[i] == 0 && i % 2 == 1) 
	    	  inversions++;
	    }
	    return (inversions % 2 == 0);
	  }
	
	public String toString(){
		String returnString = "";
		String boardString = "";
		for(int i = 0; i < board.length; i++){
			if(i % 3 == 0)
				boardString += "\n" + board[i] + " ";
			else
				boardString += board[i] + " ";
		}
		returnString = "Heuristic: " + getHeuristic() + "\nAction: " + getAction() + boardString + "\n";
		return returnString;
	}
}
