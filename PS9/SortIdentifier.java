import java.util.Arrays;
import java.util.ArrayList;
import java.text.NumberFormat;

public class SortIdentifier {
	
	public static void main(String[] args) {
		MysterySorts srts = new MysterySorts();
//		int k = 40;
		int k = 30;
		int REP = 40;
		for (int srt = 0; srt < 4; srt++) {
			try {
				//runtimes for ordered arr and shuf arr
	            int[] ordruntimeA = new int[k];
	    	    int[] shufruntimeA = new int[k];
	    	    int[] ordruntimeB = new int[k];
	    	    int[] shufruntimeB = new int[k];
	    	    
	    	    //measure two points to get ratio
	            for (int i = 0; i < k; i++) {
	            	Integer[] A = new Integer[1000];
	    	    	Integer[] B = new Integer[2000];
	    	    	//populate arr
	    	    	for (int j = 0; j < B.length; ++j) {
	    	    		if(j < A.length)
	    	    			A[j] = j; 
	    	    		B[j] = j;
	    	    	}

	    	    	//measuring runtimes
	    	    	long ordstartA = System.currentTimeMillis();
	    	    	for (int rep = 0; rep < REP; rep++) {
	    	    		srts.sort(srt,A);
	    	    	}
	    	    	long ordendA = System.currentTimeMillis();
	    	    	long ordtimeA = ordendA - ordstartA;
	    	    	ordruntimeA[i] = (int) ordtimeA;
	    			
	    	    	long ordstartB = System.currentTimeMillis();
	    	    	for (int rep = 0; rep < REP; rep++) {
	    				srts.sort(srt,B);
	    			}
	    	    	long ordendB = System.currentTimeMillis();
	    	    	long ordtimeB = ordendB - ordstartB;
	    	    	ordruntimeB[i] = (int) ordtimeB;
	    	    	
	    	    	long shufstartA = System.currentTimeMillis();
	    	    	for (int rep = 0; rep < REP; rep++) {
	    	    		srts.shuffleArray(A);
	    	    		srts.sort(srt,A);
	    	    	}
	    	    	long shufendA = System.currentTimeMillis();
	    	    	long shuftimeA = shufendA - shufstartA;
	    	    	shufruntimeA[i] = (int) shuftimeA;
	    	    	
	    	    	long shufstartB = System.currentTimeMillis();
	    	    	for (int rep = 0; rep < REP; rep++) {
	    	    		srts.shuffleArray(B);
	    	    		srts.sort(srt,B);
	    	    	}
	    	    	long shufendB = System.currentTimeMillis();
	    	    	long shuftimeB = shufendB - shufstartB;
	    	    	shufruntimeB[i] = (int) shuftimeB;
	    		}
	            
	            int ordsumA=0,ordsumB=0,shufsumA=0,shufsumB=0;
	    	    int zerocnt=0;
	    	    //sum up runtimes and get average runtimes
	    	    for (int i = 0; i < k; i++) {
	    	    	//only for ins sort since only one with
	    	    	//more than one 0 ms
	    	    	if(ordruntimeA[i] == 0)
	    	    		zerocnt++;
	    			ordsumA += ordruntimeA[i];
	    			ordsumB += ordruntimeB[i];
	    			shufsumA += shufruntimeA[i];
	    			shufsumB += shufruntimeB[i];
	    		}
	            
	    	    double ordavgA = (double)ordsumA/(double)k;
	    	    double ordavgB = (double)ordsumB/(double)k;
	    	    double shufavgA = (double)shufsumA/(double)k;
	    	    double shufavgB = (double)shufsumB/(double)k;
	            
	    	    //calculate ratios
	    	    double ordratio,shufratio;
	    	    ordratio = Math.round((ordavgB/ordavgA) * 10)/10.0;
	    	    shufratio = Math.round((shufavgB/shufavgA) * 10)/10.0;
				
	    	    System.out.println("ratios:"+ordratio+" "+shufratio);
	    	    
	    	    
	    	    if(zerocnt > 20) {
	    	    	System.out.println("insertion");
	    	    }
	    	    else if(ordratio >= 3.2 && shufratio >= 3.6)
	    	    	System.out.println("selection");
	    	    else if(ordratio >= 3.3 && (shufratio <=2.3 && shufratio >= 1.9))
	    	    	System.out.println("quick");
	    	    else
	    	    	System.out.println("heap");
				
	    	
			}catch (StackOverflowError e) {
				System.out.println("quick");
				continue;
			}
		}
	}
}


/*
public static void main(String[] args) {
		MysterySorts srts = new MysterySorts();
//		int k = 40;
		int k = 30;
		int REP = 40;
		for (int srt = 0; srt < 4; srt++) {
			try {
	            int[] ordruntimeA = new int[k];
	    	    int[] shufruntimeA = new int[k];
	    	    int[] ordruntimeB = new int[k];
	    	    int[] shufruntimeB = new int[k];
	            for (int i = 0; i < k; i++) {
	            	Integer[] A = new Integer[1000];
	    	    	Integer[] B = new Integer[2000];
	    	    	for (int j = 0; j < B.length; ++j) {
	    	    		if(j < A.length)
	    	    			A[j] = j; 
	    	    		B[j] = j;
	    	    	}

	    	    	long ordstartA = System.currentTimeMillis();
	    	    	for (int rep = 0; rep < REP; rep++) {
	    	    		srts.sort(srt,A);
	    	    	}
	    	    	long ordendA = System.currentTimeMillis();
	    	    	long ordtimeA = ordendA - ordstartA;
	    	    	ordruntimeA[i] = (int) ordtimeA;
	    			
	    	    	long ordstartB = System.currentTimeMillis();
	    	    	for (int rep = 0; rep < REP; rep++) {
	    				srts.sort(srt,B);
	    			}
	    	    	long ordendB = System.currentTimeMillis();
	    	    	long ordtimeB = ordendB - ordstartB;
	    	    	ordruntimeB[i] = (int) ordtimeB;
	    	    	
	    	    	long shufstartA = System.currentTimeMillis();
	    	    	for (int rep = 0; rep < REP; rep++) {
	    	    		srts.shuffleArray(A);
	    	    		srts.sort(srt,A);
	    	    	}
	    	    	long shufendA = System.currentTimeMillis();
	    	    	long shuftimeA = shufendA - shufstartA;
	    	    	shufruntimeA[i] = (int) shuftimeA;
	    	    	
	    	    	long shufstartB = System.currentTimeMillis();
	    	    	for (int rep = 0; rep < REP; rep++) {
	    	    		srts.shuffleArray(B);
	    	    		srts.sort(srt,B);
	    	    	}
	    	    	long shufendB = System.currentTimeMillis();
	    	    	long shuftimeB = shufendB - shufstartB;
	    	    	shufruntimeB[i] = (int) shuftimeB;
	    		}
	            
	            int ordsumA=0,ordsumB=0,shufsumA=0,shufsumB=0;
	    	    int zerocnt=0;
	    	    for (int i = 0; i < k; i++) {
	    	    	if(ordruntimeA[i] == 0)
	    	    		zerocnt++;
	    			ordsumA += ordruntimeA[i];
	    			ordsumB += ordruntimeB[i];
	    			shufsumA += shufruntimeA[i];
	    			shufsumB += shufruntimeB[i];
	    		}
	            
	    	    double ordavgA = (double)ordsumA/(double)k;
	    	    double ordavgB = (double)ordsumB/(double)k;
	    	    double shufavgA = (double)shufsumA/(double)k;
	    	    double shufavgB = (double)shufsumB/(double)k;
	            
	    	    double ordratio,shufratio;
	    	    ordratio = Math.round((ordavgB/ordavgA) * 10)/10.0;
	    	    shufratio = Math.round((shufavgB/shufavgA) * 10)/10.0;
				
	    	    System.out.println("ratios:"+ordratio+" "+shufratio);
	    	    
	    	    if(zerocnt > 20) {
	    	    	System.out.println("insertion");
	    	    }
	    	    else if(ordratio >= 3.2 && shufratio >= 3.6)
	    	    	System.out.println("selection");
	    	    else if(ordratio >= 3.3 && (shufratio <=2.3 && shufratio >= 1.9))
	    	    	System.out.println("quick");
	    	    else
	    	    	System.out.println("heap");
//	    	    else if((ordratio <= 2.3 && ordratio >= 1.9) && (shufratio <=2.3 && shufratio >= 1.9))
//	    	    	System.out.println("heap");
	    	    
	    	    
//				if(ordratio >= 9)
//					ordratio = 0.0;
//				if(shufratio >= 9)
//					shufratio = 2.2;
//				if(ordratio >= 3.3 && shufratio >= 3.4)
//					System.out.println("selection");
//				else if(ordratio <= 2 && 
//						(shufratio >= 3.3 && shufratio <= 4.2))
//					System.out.println("insertion");
//				else if(ordratio >= 3.7 && (shufratio <=2.3 && shufratio >= 2.0))
//					System.out.println("quick");
//				else if(ordratio <= 2.4 && shufratio <= 2.3)
//					System.out.println("heap");
				
			}catch (StackOverflowError e) {
				System.out.println("quick");
				continue;
			}
		}
	}
	
	*/





/*
public static void main(String[] args) {
		MysterySorts s = new MysterySorts();
		int n = 5000; //size of array of integers
	      int k = 4; //size of array of runtimes
	      Integer[] A = new Integer[n];
	      Integer[] B = new Integer[n*2];
	      int[] sdata0 = new int[k]; // to store runtimes for short sorted array
	      int[] sdata1 = new int[k]; // to store runtimes for long sorted array
	      int[] udata0 = new int[k]; // to store runtimes for short shuffled arrays
	      int[] udata1 = new int[k]; // to store runtimes for long shuffled arrays
	      for (int j = 0; j < n*2; ++j) {
	    	  if(j < A.length)
	    			A[j] = j;
	    	  B[j] = j;
	      }
	   
	      for (int i = 0; i < k; i++)
	      {
	            //record the runtimes for short sorted array
	            long start = System.currentTimeMillis(); //log start time
	            for (int j = 0; j < 10; j++)
	            {
	               s.sort(i, A); //sort the array 10 times
	            }
	            long now = System.currentTimeMillis(); //log end time
	            long elapsed = now - start; //runtime of each sorting algorithm
	            sdata0[i] = (int) elapsed;
	            
	            //record the runtimes for long sorted array
	            start = System.currentTimeMillis(); //log start time
	            for (int j = 0; j < 10; j++)
	            {
	               s.sort(i, B); //sort the array 10 times
	            }
	            now = System.currentTimeMillis(); //log end time
	            elapsed = now - start; //runtime of each sorting algorithm
	            sdata1[i] = (int) elapsed;
	      
	            s.shuffleArray(A); //shuffle A
	            s.shuffleArray(B); //shuffle B
	      
	            //record the runtimes for short shuffled array
	            start = System.currentTimeMillis(); //log start time
	            for (int j = 0; j < 10; j++)
	            {
	               s.sort(i, A);
	            }
	            now = System.currentTimeMillis(); //log end time
	            elapsed = now - start; //runtime of each sorting algorithm
	            udata0[i] = (int) elapsed; 
	                     
	            //record the runtimes for long shuffled array
	            start = System.currentTimeMillis(); //log start time
	            for (int j = 0; j < 10; j++)
	            {
	               s.sort(i, B);
	            }
	            now = System.currentTimeMillis(); //log end time
	            elapsed = now - start; //runtime of each sorting algorithm
	            udata1[i] = (int) elapsed;
	      }
	      
	      //print the runtime of each sort algo for short & long sorted & shuffled array
	      System.out.println("sorted");
	      for (int i = 0; i < k; i++)
	      {
	          System.out.println(i + " short: " + sdata0[i] +", long: " + sdata1[i]); 
	      }
	      
	      System.out.println("shuffled");
	      for (int i = 0; i < k; i++)
	      {
	         System.out.println(i + " short: " + udata0[i] +", long: " + udata1[i]); 
	      }
	      //set min to find smallest runtime
	      int min = 10;
	      int max = 10;
	      for (int i = 0; i < k; i++)
	      {
	         if (sdata0[i] < min)
	         {
	            min = sdata0[i];
	         }
	         if (sdata0[i] > max)
	         {
	            max = sdata0[i];
	         }
	      }
	      //identifying the sorting algorithm
	      for (int i = 0; i < k; i++)
	      {
	         int num1 = sdata1[i] / sdata0[i];
	         int num2 = udata1[i] / udata0[i];
	         if (num1 == 1 && num2 == 2) 
	         {
	            System.out.println("heap");
	         }
	         else if (num1 == 4 || num2 == 4)
	         {
	            System.out.println("insertion");
	         }
	         else if (num1 == 3 && num2 == 3 && sdata0[i] == max) //best case for selection sort is n^2 (runtime doubles if size doubles)
	         { 
	            System.out.println("selection");
	         } 
	         else
	         { 
	            System.out.println("quick");
	         }
	       }
	}
	
*/
