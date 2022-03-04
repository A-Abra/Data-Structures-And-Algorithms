import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

public class SmallWorld {

	public static void main(String[] args) throws Exception {
		ArrayList<ActorRecord> act = new ArrayList<>();
		BST<String, MovieRecord> mviBST = new BST<String, MovieRecord>();
		ArrayList<String> actors = new ArrayList<>();

//		String[] fnames = {"actresses.list.gz","actors.list.gz"};
//		String[] fnames = {"actors.list.gz"};
		String[] fnames = {"actresses.list.gz"};
//		String[] fnames = {"shortestActors.list.gz"};
		Integer count = 0;
		String content;
		String[] tkn;

		//read files and store in movieBST
		for (String file : fnames) {
			RetrieveActors ra = new RetrieveActors(file);
			if(count>180000)break;
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
		}
		//free up space
		count = null;tkn=null;content=null;fnames=null;
		
		//create graph
		int gsize =act.size();
		Graphl g = new Graphl(gsize);
		for (MovieRecord movie : mviBST.values()) {
			int masize = movie.actors.size();
			for (int actori = 0; actori < masize; actori++) {
				for (int actorj = actori+1; actorj < masize; actorj++) {
					g.setEdge(movie.actors.get(actori), movie.actors.get(actorj), 1);
					g.setEdge(movie.actors.get(actorj), movie.actors.get(actori), 1);
				}
			}
		}
		mviBST = null;
		
		
        int ind1 = 1, ind2 = 1;
		Scanner scan = new Scanner(System.in);
		Deque<Integer> path = new ArrayDeque<Integer>();
		
		while(true) {
			System.out.println("Enter source and destination indices:");
			ind1 = scan.nextInt();
			ind2 = scan.nextInt();
			if((ind1 <= 0) && (ind2 <= 0)) break;
			//marking nodes unvisited
			for(int i=0; i<g.n(); i++)
				g.setMark(i, -1);
			
			//find shortest path
			findshortestpath(g, ind1, ind2);
			if(g.getMark(ind2)==-1) {
				System.out.println("No path between "+actors.get(ind1)+" and "+actors.get(ind2));
			} else {
				path.addFirst(ind2);
				int curr = ind2;
				int pred = g.getMark(curr);

				while(pred > -2){
					for(String s : act.get(curr).movies) {
						if(act.get(pred).appearedIn(s)) break;
					}
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
		
	}
    
    
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
}
