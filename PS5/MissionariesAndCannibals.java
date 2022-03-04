import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;

public class MissionariesAndCannibals {

	public static void main(String[] args) {
		int N = 3;
		State.setN(N);
		int numberTrips = -1;
		ArrayDeque<State> toExplore = new ArrayDeque<State>();
		ArrayList<State> visited = new ArrayList<State>();
		ArrayDeque<State> soln = new ArrayDeque<State>();
		State start = new State(N,N,0);
		toExplore.addFirst(start);
		visited.add(start);
		while(!toExplore.isEmpty()) {
			State next = toExplore.removeFirst();
			next.display();
			if(next.m == 0 && next.c == 0 && next.b == 1) {
				for (State x = next; x != null; x = x.pred) {
					soln.offerLast(x);
				}
				numberTrips = soln.size()-1;
				System.out.println("Solution:");
				State s = soln.pollLast();
				while(s != null) {
					s.display();
					s=soln.pollLast();
				}
				break;
			}
			
			for(int i = 0; i<= 2; i++)
				for (int j = 0; i+j <= 2; j++) {
					if (i == 0 && j == 0)
						continue;
					State p = next.move(i, j);
					if(!p.legal())
						continue;
					boolean beenHere = false;
					for (State s : visited) {
						if((s.equiv(p))) {
							beenHere = true;
							break;
						}
					}
					if(beenHere)
						continue;
					toExplore.addFirst(p);
					visited.add(p);
				}
		}
		System.out.println("It required "+numberTrips+" crossings");
	}
}
