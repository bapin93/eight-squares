package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		boolean solved = false;
		double timeStart = 0;
		double timeElapsed = 0;
		Builder b = new Builder();
		Node node = new Node();
		
		System.out.print("1 - Enter custom board\n2 - Generate a random board\nEnter menu choce -->");
		int menuChoice = scan.nextInt();
		while(menuChoice != 1 && menuChoice != 2){
			System.out.println("Invalid Input: Enter 1 or 2 from the following menu.\n" +
		                       "1 - Enter custom board\n2 - Generate a random board\nEnter menu choce -->");
			menuChoice = scan.nextInt();
		}
		switch(menuChoice){
		case 1:
			String temp = "";
			int[] tryThis = new int [9];
			System.out.println("Input the first number");
			String boardString = "";
			for(int i = 0; i < tryThis.length; i++){
				int num = scan.nextInt();
				
				while (temp.contains(num + "")) {
					System.out.println("You already input that number. Slect another");
					num = scan.nextInt();
				}
				tryThis[i] = num;
				temp += "" + num;
				
				boardString = "";
				for(int j = 0; j < tryThis.length - (8-i); j++){
					if(j % 3 == 0)
						boardString += "\n" + tryThis[j] + " ";
					else
						boardString += tryThis[j] + " ";
				}
				System.out.println(boardString);
				System.out.println("Input the next number");
			}
			node.setBoard(tryThis);
			node.calcHeuristic();
			break;
		case 2:
			break;	
		}
		while(!node.isSolvable()){
			System.out.println("Board not solvable...\nGenerating new board...");
			node = new Node();
			node.setPathCost(0);
			node.setAction("NULL");
		}
		b.addNode(node);
		if(checkSolution(b))
			solved = true;
		else{
			System.out.println("Solving Puzzle...");
			b.split(node);
			timeStart = System.currentTimeMillis();
			while(!solved){
				if(checkSolution(b))
					solved = true;
				else{
					for(Node n : b.getSplitList()){
						if(compareBoards(b.getNotSplitList().get(0).getBoard(), n.getBoard())){
							b.getNotSplitList().remove(0);
						}
					}
					b.split(b.getNotSplitList().get(0));
				}
			}//END WHILE
		}//END ELSE
		double timeStop = System.currentTimeMillis();
	    timeElapsed = (timeStop - timeStart)/1000; 
		System.out.println("Found Solution in: " + timeElapsed + " seconds");
		System.out.println(getActions(b.getNotSplitList().get(0)));
		System.out.println(node.toString());
		scan.close();
	}//END MAIN

	public static String getActions(Node n){
		ArrayList<String> actionString = new ArrayList<String>();
		while(n.getParent() != null){
			actionString.add(n.getAction());
			n = n.getParent();
		}
		String returnString = "";
		for(int i = actionString.size() - 1; i >= 0; i--){
			returnString += actionString.get(i) + "   ";
		}
		return returnString;
	}

	public static boolean compareBoards(int[] a1, int[] a2){
		boolean same = true;
		int count = 0;
		for(int i = 0; i < a1.length; i++){
			if(a1[i] != a2[i])
				count++;
		}
		if(count > 0)
			same = false;
		else
			same = true;
		return same;
	}

	public static boolean checkSolution(Builder b){
		boolean solved = false;
		int count = 0;
		for(int i = 0; i < b.getNotSplitList().get(0).getBoard().length; i++){
			if(b.getNotSplitList().get(0).getBoard()[i] != b.getNotSplitList().get(0).getSolution()[i]){
				count++;
			}
		}
		if(count > 0)
			solved = false;
		else
			solved = true;
		return solved;
	}
}