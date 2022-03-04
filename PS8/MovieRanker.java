//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//import java.util.List;
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.Collections;
//
///* Starter code for PS8.
// */
//
//public class MovieRanker {
//
//    public static void main(String[] args) {
//        File file = new File("ratings.tsv");
//        int minVotes = 1;
//        int numRecords = 1;
//        Scanner input = new Scanner(System.in);
//        while(true) {
//            System.out.println();
//            System.out.println("Enter minimum vote threshold and number of records:");
//            minVotes = input.nextInt();
//            numRecords = input.nextInt();
//            MaxHeap<MovieRating> rl = new MaxHeap<>();
//            if (minVotes * numRecords == 0)
//                break;
//            long startTime = System.currentTimeMillis();
//            try {
//                Scanner scanner = new Scanner(file,"UTF-8");
//                while (scanner.hasNextLine()) {
//                    String line = scanner.nextLine();
//                    String[] tkns = line.split("\\t"); // tabs separate tokens
//                    MovieRating nr = new MovieRating(tkns[0], tkns[1], tkns[2]);
//                    
//                    if(nr.getVotes() > minVotes)
//                        rl.insert(nr);
//                }
//                scanner.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            
//            for (int i = 0; i < numRecords; i++) {
//				System.out.println(rl.removemax());
//			}
//            
//            System.out.println();
//            long readTime = System.currentTimeMillis();
//            System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" ms");
//        }//loop end
//    }
//}

//MaxHeap<MovieRating> max = new MaxHeap<>();
//MaxHeap<MovieRating> maxneg = new MaxHeap<>();
//while(!rl.isEmpty()) {
//    MovieRating someMvi = rl.removemax();
//    if((maxneg.heapsize() < numRecords)) {
//        someMvi.setRating(someMvi.getRating()*-1);
//        maxneg.insert(someMvi);
//    }
//    else if(maxneg.heapsize() == numRecords) {
////    	while(!maxneg.isEmpty()) {
////    		MovieRating maxMvi = maxneg.removemax();
////    		maxMvi.setRating(maxMvi.getRating()*-1);
////    		max.insert(maxMvi);
////    	}
//    	break;
//    	rl.
//    }
//}
//
//while(!maxneg.isEmpty()) {
//    System.out.println(maxneg.removemax());
//}


/**************************************************/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;


public class MovieRanker {

    public static void main(String[] args) {
        File file = new File("ratings.tsv");
        //arraylist of movie ratings
        ArrayDeque<MovieRating> rl = new ArrayDeque<MovieRating>();
        
        try {
            Scanner scanner = new Scanner(file,"UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tkns = line.split("\\t"); // tabs separate tokens
                MovieRating nr = new MovieRating(tkns[0], tkns[1], tkns[2]);
                rl.add(nr);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int minVotes = 1;
        int numRecords = 1;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Enter minimum vote threshold and number of records:");
            ArrayDeque<MovieRating> copy = rl.clone();
            //num of reviews
            minVotes = input.nextInt();
            //num of movies to print
            numRecords = input.nextInt();
            
            if (minVotes * numRecords == 0)
                break;
            long startTime = System.currentTimeMillis();

            MaxHeap<MovieRating> max = new MaxHeap<>();
            while(!copy.isEmpty()) {
                MovieRating rating = copy.removeFirst();
                //go thorugh ratings and insert into heap
                //if minimum votes met
                rating.setRating(rating.getRating()*-1);
                if(rating.getVotes() >= minVotes)
                    max.insert(rating);
            }
            
            //print all heap content until num records met
            for (int i = 0; i < numRecords; i++) {
                if(!max.isEmpty()) {
                    MovieRating rate = max.removemax();
                    System.out.println(rate);
                }
            }

            System.out.println();
            long readTime = System.currentTimeMillis();
            System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" ms");
        }
    }
}



/******************************/
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//import java.util.List;
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.Collections;
//
///* Starter code for PS8.
// */
//
//public class MovieRanker {
//
//    public static void main(String[] args) {
//        File file = new File("ratings.tsv");
//
//        int minVotes = 1;
//        int numRecords = 1;
//        Scanner input = new Scanner(System.in);
//        while (true) {
//            System.out.println();
//            System.out.println("Enter minimum vote threshold and number of records:");
//
//            minVotes = input.nextInt();
//            numRecords = input.nextInt();
//            if (minVotes * numRecords == 0)
//                break;
//            long startTime = System.currentTimeMillis();
//            
//            ArrayDeque<MovieRating> rl = new ArrayDeque<MovieRating>();
//            
//            try {
//                Scanner scanner = new Scanner(file,"UTF-8");
//                while (scanner.hasNextLine()) {
//                    String line = scanner.nextLine();
//                    String[] tkns = line.split("\\t"); // tabs separate tokens
//                    MovieRating nr = new MovieRating(tkns[0], tkns[1], tkns[2]);
//                    if(nr.getVotes() > minVotes)
//                      rl.add(nr);
//                }
//                scanner.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            MaxHeap<MovieRating> max = new MaxHeap<>();
//            while(!rl.isEmpty()) {
//                max.insert(rl.remove());
//            }
//            
//            for (int i = 0; i < numRecords; i++) {
//				System.out.println(max.removemax());
//			}
//
//            System.out.println();
//            long readTime = System.currentTimeMillis();
//            System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" ms");
//        }
//    }
//}
