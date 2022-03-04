public class BoardState {
	String board;
	BoardState pred; //predecessor state
	
	BoardState(BoardState s) {
		board = s.board;
	}
	
	BoardState(String s) {
		board = s;
	}
	
	//check to see if solvable
	boolean legal(String s) {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = i+1; j < 9; j++) {
				if(s.charAt(i)>s.charAt(j) && s.charAt(i) < '9' && s.charAt(j) < '9')
					count++;
			}
		}
		return (count%2 == 0);
	}
	
	//move methods
	BoardState moveLeft(String s, int bpos, boolean arr[]) {
		BoardState newState = new BoardState(this);
		
		String str = s;
		if(bpos != 0 && bpos != 3 && bpos != 6) {
			char a = s.charAt(bpos-1);
			String moved = str.substring(0,bpos) + a + str.substring(bpos + 1);
			str = moved.substring(0,(bpos-1)) + '9' + moved.substring(bpos);
			newState.board = str;
		}
		//check if state visited to mark true meaning it is visited
		//and return move created
		if(arr[Integer.parseInt(str)] == false) {
			arr[Integer.parseInt(str)] = true;
			newState.pred = this;
			return newState;
		}
		//else can't make a move (in this case cant move left)
		return null;
	}
	
	BoardState moveRight(String s, int bpos, boolean arr[]) {
		BoardState newState = new BoardState(this);

		String str = s;
		if(bpos != 2 && bpos != 5 && bpos != 8) {
			char a = s.charAt(bpos + 1);
			String moved = str.substring(0,bpos) + a + str.substring(bpos + 1);
			str = moved.substring(0,(bpos + 1)) + '9' + moved.substring(bpos + 2);
			newState.board = str;
		}
		if(arr[Integer.parseInt(str)] == false) {
			arr[Integer.parseInt(str)] = true;
			newState.pred = this;
			return newState;
		}
		return null;
	}
	
	BoardState moveUp(String s, int bpos, boolean arr[]) {
		BoardState newState = new BoardState(this);

		String str = s;
		if(!(bpos < 3)) {
			char a = s.charAt(bpos - 3);
			String moved = str.substring(0,bpos) + a + str.substring(bpos + 1);
			str = moved.substring(0,(bpos - 3)) + '9' + moved.substring(bpos - 2);
			newState.board = str;
		}
		if(arr[Integer.parseInt(str)] == false) {
			arr[Integer.parseInt(str)] = true;
			newState.pred = this;
			return newState;
		}
		return null;
	}
	
	BoardState moveDown(String s, int bpos, boolean arr[]) {
		BoardState newState = new BoardState(this);

		String str = s;
		if(!(bpos > 5)) {
			char a = s.charAt(bpos + 3);
			String moved = str.substring(0,bpos) + a + str.substring(bpos + 1);
			str = moved.substring(0,(bpos + 3)) + '9' + moved.substring(bpos + 4);
			newState.board = str;
		}
		if(arr[Integer.parseInt(str)] == false) {
			arr[Integer.parseInt(str)] = true;
			newState.pred = this;
			return newState;
		}
		return null;
	}
	
	//display the board
		void display() {
//			System.out.println(board);
			System.out.println(board.substring(0, 3));
			System.out.println(board.substring(3, 6));
			System.out.println(board.substring(6, 9));
			System.out.println();
		}
}