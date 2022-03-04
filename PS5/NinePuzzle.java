import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class NinePuzzle {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BoardState inputState = new BoardState(in.nextLine());
		ArrayDeque<BoardState> toExplore = new ArrayDeque<BoardState>();
		ArrayDeque<BoardState> soln = new ArrayDeque<BoardState>();
		boolean visited[] = new boolean[987654322];
		String goalStr = "123456789";
		BoardState current;
		BoardState tempMove;
		//123459786
		//123489765
		
		//first check if solvable
		if(inputState.legal(inputState.board)) {
			//add the inputstate to explored and visited to start process
			toExplore.add(inputState);
			visited[Integer.parseInt(inputState.board)] = true;
			
			//loop through to explore all states
			while (!toExplore.isEmpty()) { 
				//take the first state in dequeue to check if reached goal
				current = toExplore.removeFirst();
				int blankPos = current.board.indexOf('9');
				if(current.board.equals(goalStr)) {
					//prints out the previous moves leading up to goal
					for(BoardState x = current; x != null; x = x.pred) {
						soln.offerLast(x);
					}
					BoardState s = soln.pollLast();
					while(s!= null) {
						s.display();
						s=soln.pollLast();
					}
					break;
				}
				//branch out to possible moves if not equal to goalstate
				//until goal reached
//				else {
//					visited[Integer.parseInt(current.board)] = true;
//					tempMove=current.moveLeft(current.board,blankPos,visited);
//					if(tempMove != null)
//						toExplore.add(tempMove);
//					tempMove=current.moveRight(current.board,blankPos,visited);
//					if(tempMove != null)
//						toExplore.add(tempMove);
//					tempMove=current.moveUp(current.board,blankPos,visited);
//					if(tempMove != null)
//						toExplore.add(tempMove);
//					tempMove=current.moveDown(current.board,blankPos,visited);
//					if(tempMove != null)
//						toExplore.add(tempMove);
//				}
				
				
				//force to move blankspace up when at center
				else {
					visited[Integer.parseInt(current.board)] = true;
					if(blankPos==4) {
						tempMove=current.moveRight(current.board,blankPos,visited);
						if(tempMove != null)
							toExplore.add(tempMove);
					}
					else {
						tempMove=current.moveLeft(current.board,blankPos,visited);
						if(tempMove != null)
							toExplore.add(tempMove);
						tempMove=current.moveRight(current.board,blankPos,visited);
						if(tempMove != null)
							toExplore.add(tempMove);
						tempMove=current.moveUp(current.board,blankPos,visited);
						if(tempMove != null)
							toExplore.add(tempMove);
						tempMove=current.moveDown(current.board,blankPos,visited);
						if(tempMove != null)
							toExplore.add(tempMove);
					}
				}
			}
		}
		//not solvable
		else
			System.out.println("-1");
	}
}
