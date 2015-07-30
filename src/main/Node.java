package main;

import java.util.Random;

/**
 * @author andres
 *
 */
@SuppressWarnings("rawtypes")
public class Node implements Comparable{

	private int[] _board = new int[9];
	private int[] _solution = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
	private Node _parent;
	private String _action;
	private int _pathCost;
	private int _heuristic;

	/**
	 * no-args constructor
	 */
	public Node() {
		_heuristic = 0;
		_pathCost = 0;
		_parent = null;
		_action = "NULL";
		
		Random random = new Random();
		String temp = "";
		for (int x = 0; x < _board.length; x++) {
			int num = random.nextInt((8 - 0) + 1);

			while (temp.contains(num + "")) {
				num = random.nextInt((8 - 0) + 1);
			}
			_board[x] = num;
			temp += "" + num;
		}
		calculateHeuristic();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Object n) {
		Node n1 = (Node)n;
		int compareHeu = (int)(n1.getHeuristic());
		return (int)this._heuristic - compareHeu;
	}
	
	/**
	 * @return the parent of this node
	 */
	public Node getParent(){
		return _parent;
	}
	
	/**
	 * @return the action that was performed on this node
	 */
	public String getAction(){
		return _action;
	}
	
	/**
	 * @return the number of actions it has taken to get to this node
	 */
	public int getPathCost(){
		return _pathCost;
	}
	
	/**
	 * @return the board
	 */
	public int[] getBoard() {
		return _board;
	}
	
	/**
	 * @return a representation of the solution
	 */
	public int[] getSolution(){
		return _solution;
	}
	
	/**
	 * @return the nodes heuristic
	 */
	public int getHeuristic(){
		return _heuristic;
	}
	
	/**
	 * @param action the action performed on this node
	 */
	public void setAction(final String action){
		_action = action;
	}
	
	/**
	 * @param parent 
	 */
	public void setParent(final Node parent){
		_parent = parent;
	}
	
	/**
	 * @param pathCost
	 */
	public void setPathCost(final int pathCost){
		_pathCost = pathCost;
	}

	/**
	 * @param newHeuristic
	 */
	public void setHeuristic(final int newHeuristic){
		_heuristic = newHeuristic;
	}
	
	/**
	 * @param newBoard
	 */
	public void setBoard(final int[] newBoard){
		for(int i = 0; i < _board.length; i++){
			_board[i] = newBoard[i];
		}
	}
	
	/**
	 * @return the index where the space is (0)
	 */
	public int findZero() {
		int index = 0;
		for (int i = 0; i < _board.length; i++) {
			if (_board[i] == 0) {
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * 
	 */
	public void calculateHeuristic(){
		int value = 0;
		for(int i = 0; i < _board.length; i++){
			if(getBoard()[i] != _solution[i])
				value++;
		}
	    value += getPathCost();
		setHeuristic(value);
	}
	
	/**
	 * @return boolean whether the board is actually solvable or not. 
	 * Not all boards are solvable
	 */
	public boolean isSolvable() {
	    int inversions = 0; 
	    for(int i = 0; i < _board.length - 1; i++) {
	      for(int j = i + 1; j < _board.length; j++)
	        if(_board[i] > _board[j]) 
	        	inversions++;
	      if(_board[i] == 0 && i % 2 == 1) 
	    	  inversions++;
	    }
	    return (inversions % 2 == 0);
	  }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String returnString = "";
		String boardString = "";
		for(int i = 0; i < _board.length; i++){
			if(i % 3 == 0)
				boardString += "\n" + _board[i] + " ";
			else
				boardString += _board[i] + " ";
		}
		returnString = "Heuristic: " + getHeuristic() + "\nAction: " + getAction() + boardString + "\n";
		return returnString;
	}
}
