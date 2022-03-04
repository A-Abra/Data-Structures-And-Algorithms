import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class SmallWorld {

	public static void main(String[] args) throws Exception {
		ArrayList<ActorRecord> act = new ArrayList<ActorRecord>();
		BST<String, MovieRecord> mviBST = new BST<String, MovieRecord>();
		ArrayList<String> actors = new ArrayList<String>();

		String fname = "shortestActors.list.gz";
//		String fname = "actresses.list.gz";
		RetrieveActors ra = new RetrieveActors(fname);
		//RetrieveActors ra = new RetrieveActors(System.getenv("LIB")+"/public/"+fname);
		int count = 0;
		String content;
		String[] tkn;
	
		//read file and store in movieBST
		while ((content = ra.getNext()) != null) {
			tkn = content.split("@@@");
			actors.add(tkn[0]);
			ActorRecord ar = new ActorRecord(tkn[0]);
			for (int i = 1; i < tkn.length; ++i) {
				if (tkn[i].substring(0, 2).equals("FM"))
					ar.addMovie(tkn[i].substring(2));
			}
			for (String mvi : ar.movies) {
				MovieRecord mvirec = mviBST.find(mvi);
				if(mvirec == null) {
					mvirec = new MovieRecord(mvi);
					mvirec.addActor(count);
					mviBST.insert(mvi, mvirec);
				}
				else {
					mvirec.addActor(count);
				}
			}
			act.add(ar);
			++count;
		}
		
		//create graph
		Graphl g = new Graphl(act.size());
		for (MovieRecord movie : mviBST.values()) {
			for (int actori = 0; actori < movie.actors.size(); actori++) {
				for (int actorj = actori+1; actorj < movie.actors.size(); actorj++) {
					g.setEdge(movie.actors.get(actori), movie.actors.get(actorj), 1);
					g.setEdge(movie.actors.get(actorj), movie.actors.get(actori), 1);
				}
			}
		}
		//free up space
		mviBST = null;

//		int ind1 = 1, ind2 = 1;
//		Scanner scan = new Scanner(System.in);
//		System.out.println("  01234567");
//		for (int i = 0; i < g.matrix.length; i++) {
//			System.out.print(i+" ");
//			for (int j = 0; j < g.matrix[i].length; j++) {
//				System.out.print(g.matrix[i][j]);
//			}
//			System.out.println();
//		}
		
		
		int ind1 = 1, ind2 = 1;
		Scanner scan = new Scanner(System.in);
		Deque<Integer> path = new ArrayDeque<Integer>();
		
		while(true) {
			System.out.println("Enter source and destination indices:");
			ind1 = scan.nextInt();
			ind2 = scan.nextInt();
			if((ind1 <= 0) && (ind2 <= 0)) break;
			for(int i=0; i<g.n(); i++)
				g.setMark(i, -1);
			
			//finding shortest path
			findshortestpath(g, ind1, ind2);
			//for if destination not reached
			if(g.getMark(ind2)==-1) {
				System.out.println("No path between "+actors.get(ind1)+" and "+actors.get(ind2));
				ind2 = 1;
				ind1 = 1;
			} else {
				path.addFirst(ind2);
				int curr = ind2;
				int pred = g.getMark(curr);

				//adding nodes to queue
				while(pred > -2){
					//looking through to find which actors with who
					for(String s : act.get(curr).movies) {
						if(act.get(pred).appearedIn(s)) break;
					}
					//to then add to queue
					curr = pred;
					path.addFirst(curr);
					pred = g.getMark(curr);
				}
				
				
				curr = path.removeFirst();
				//printing path between two indices
				System.out.println("Shortest path between "+
				actors.get(ind1)+" and "+actors.get(ind2));
				System.out.println("Distance: "+path.size()+"; the chain is:");
				
				//printing out path and films
				while(!path.isEmpty()){
					pred = path.removeFirst();
					String actor1 = actors.get(curr);
					String actor2 = actors.get(pred);
					System.out.print(actor1+" appeared with "+actor2);
					String film = "";
					for(String s : act.get(curr).movies) {
						if(act.get(pred).appearedIn(s)){
							film = s;
							break;
						}
					}
					System.out.println(" in "+film);
					curr = pred;
				}
				System.out.println();
			}
		}
		
	} //*****MAIN*****
	
	public static void findshortestpath(Graphl g, int ind1, int ind2) {
		LinkedList<Integer> Q = new LinkedList<Integer>();
		Q.add(ind1);
		//start marked as -2
		g.setMark(ind1, -2);
		while (Q.size() > 0) {
			int curr = Q.removeFirst();
			for (int i : g.neighbors(curr)) {
				//checking if unvisited and adding to Q
				if (g.getMark(i) == -1) {
					g.setMark(i, curr);
					Q.add(i);
				}
				//return if found destination
				if(i==ind2)
					return;
			}
		}
	}

} //end of class


