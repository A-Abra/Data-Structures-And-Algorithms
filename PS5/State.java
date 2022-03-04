
public class State {
	static int N; // There are N missionaries and N Cannibals
	int m; // missionaries on left bank
	int c; // cannibals on left bank
	int b; // 0 for boat on left bank, 1 for boat on the right
	State pred; // predecessor state
	
	State(State s) {
		m = s.m;
		c = s.c;
		b = s.b;
	}
	
	State(int M, int C, int B) {
		m = M;
		c = C;
		b = B;
	}
	
	static void setN(int n) {
		N = n;
	}
	
	boolean legal() {
		if(m < 0 || m > N || c < 0 || c > N)
			return false;
		if(m > 0 && c > m)
			return false;
		if(N - m > 0 && N - c > N - m)
			return false;
		return true;
	}
	
	boolean equiv(State s) {
		if(s.m == m && s.c == c && s.b == b)
			return true;
		else
			return false;
	}
	
	State move(int M, int C) {
		State newState = new State(this);
		if(b == 0) {
			newState.m = m - M;
			newState.c = c - C;
			newState.b = 1;
		}else {
			newState.m = m + M;
			newState.c = c + C;
			newState.b = 0;
		}
		newState.pred = this;
		return newState;
	}
	
	void display(){
		//System.out.println(m + " " + c + " " + (b == 0 ? "Left" : "Right"));
		System.out.println(m+""+c+""+b);
	}
}
