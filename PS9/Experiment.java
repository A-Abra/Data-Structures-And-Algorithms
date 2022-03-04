import java.util.ArrayList;
import java.util.Arrays;
import java.text.NumberFormat;

/* Program sorts many arrays using one of the "Mystery" sorts
 * sort1-sort4. You can plot the run times using Plot (if compatable
 * with where you are running the program) or simply print out the
 * results and plot with any plotting software.
 */

public class Experiment {
	public static void main(String[] args) {
	    MysterySorts srts = new MysterySorts();
//	    int k = 30;
//		int REP = 40;
	    int k = 10;
		int REP = 40;

		for (int da = 0; da < k; da++) {
			
		
		
		
	    int[] ordruntimeA = new int[k];
	    int[] shufruntimeA = new int[k];
	    int[] ordruntimeB = new int[k];
	    int[] shufruntimeB = new int[k];
	    
	    
	    
	    for (int i = 0; i < k; ++i) {
	    	Integer[] A = new Integer[1000];
	    	Integer[] B = new Integer[2000];
	    	for (int j = 0; j < B.length; ++j) {
	    		if(j < A.length)
	    			A[j] = j; 
	    		B[j] = j;
	    	}
	    	
	    	long ordstartA = System.currentTimeMillis();
	    	for (int rep = 0; rep < REP; rep++) {
	    		srts.sort(3,A);
	    	}
	    	long ordendA = System.currentTimeMillis();
	    	long ordtimeA = ordendA - ordstartA;
	    	ordruntimeA[i] = (int) ordtimeA;
	    	
	    	long ordstartB = System.currentTimeMillis();
	    	for (int rep = 0; rep < REP; rep++) {
				srts.sort(3,B);
			}
	    	long ordendB = System.currentTimeMillis();
	    	long ordtimeB = ordendB - ordstartB;
	    	ordruntimeB[i] = (int) ordtimeB;
	    	
	    	long shufstartA = System.currentTimeMillis();
	    	for (int rep = 0; rep < REP; rep++) {
	    		srts.shuffleArray(A);
	    		srts.sort(3,A);
	    	}
	    	long shufendA = System.currentTimeMillis();
	    	long shuftimeA = shufendA - shufstartA;
	    	shufruntimeA[i] = (int) shuftimeA;
	    	
	    	long shufstartB = System.currentTimeMillis();
	    	for (int rep = 0; rep < REP; rep++) {
	    		srts.shuffleArray(B);
	    		srts.sort(3,B);
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
	    
//	    System.out.println(ordavgA+" "+ordavgB);
	    
//	    for (int i = 0; i < shufruntimeB.length; i++) {
//			System.out.println(ordruntimeA[i]+" "+ordruntimeB[i]);
//		}
	    
	    double ordratio,shufratio;
	    ordratio = Math.round((ordavgB/ordavgA) * 10)/10.0;
	    shufratio = Math.round((shufavgB/shufavgA) * 10)/10.0;
	    
	    System.out.println(zerocnt);
	    System.out.println("ratios:"+ordratio+" "+shufratio);
		}//test loop
//	     for(int i=0;i<k;++i)
//	     System.out.println(data[i]);
	    
//	    double ordavg = (((double) ordruntime[19] / (double) ordruntime[9]) + 
//	    		((double) ordruntime[11]/ (double) ordruntime[5]))/2.0;
//	    double ordratio = Math.round(ordavg * 10)/10.0;
//	    double shufavg = (((double) shufruntime[19] / (double) shufruntime[9]) + 
//	    		((double) shufruntime[11]/ (double) shufruntime[5]))/2.0;
//	    double shufratio = Math.round(shufavg * 10)/10.0;
//	    System.out.println("Order ms:"+ordruntime[10]+" "+ordruntime[21]);
//	    System.out.println("Shuf ms:"+shufruntime[10]+" "+shufruntime[21]);
//	    System.out.println("Order:"+ordratio+" Shuf:"+shufratio);
	    
//	    double ordavg = (((double) ordruntime[21] / (double) ordruntime[10]) + 
//	    		((double) ordruntime[11]/ (double) ordruntime[5]))/2.0;
//	    double ordratio = Math.round(ordavg * 10)/10.0;
//	    double shufavg = (((double) shufruntime[21] / (double) shufruntime[10]) + 
//	    		((double) shufruntime[11]/ (double) shufruntime[5]))/2.0;
//	    double shufratio = Math.round(shufavg * 10)/10.0;
//	    System.out.println("Order ms:"+ordruntime[10]+" "+ordruntime[21]);
//	    System.out.println("Shuf ms:"+shufruntime[10]+" "+shufruntime[21]);
//	    System.out.println("Order:"+ordratio+" Shuf:"+shufratio);
	  }
}

/*

public class Experiment {
	public static void main(String[] args) {
	    MysterySorts srts = new MysterySorts();
	    int n;
	    int k = 20;
		int REP = 120;
//	    int k = 22;
//	    int REP = 70;

	    int[] ordruntime = new int[k];
	    int[] shufruntime = new int[k];
	    for (int i = 0; i < k; ++i) { 
	    	n = (i + 1) * REP;
	    	Integer[] A = new Integer[n];
	    	for (int j = 0; j < A.length; ++j)
	    		A[j] = j;

	    	long ordstart = System.currentTimeMillis();
	    	for (int rep = 0; rep < REP; rep++) {
	    		srts.sort(2,A);
	    	}
	    	long ordend = System.currentTimeMillis();
	    	long ordtime = ordend - ordstart;
	    	ordruntime[i] = (int) ordtime;

	    	long shufstart = System.currentTimeMillis();
	    	for (int rep = 0; rep < REP; rep++) {
	    		srts.shuffleArray(A);
	    		srts.sort(2,A);
	    	}
	    	long shufend = System.currentTimeMillis();
	    	long shuftime = shufend - shufstart;
	    	shufruntime[i] = (int) shuftime;
	    }
	    Plot.view(ordruntime);
	    Plot.view(shufruntime);
//	     for(int i=0;i<k;++i)
//	     System.out.println(data[i]);
	    
//	    double ordavg = (((double) ordruntime[19] / (double) ordruntime[9]) + 
//	    		((double) ordruntime[11]/ (double) ordruntime[5]))/2.0;
//	    double ordratio = Math.round(ordavg * 10)/10.0;
//	    double shufavg = (((double) shufruntime[19] / (double) shufruntime[9]) + 
//	    		((double) shufruntime[11]/ (double) shufruntime[5]))/2.0;
//	    double shufratio = Math.round(shufavg * 10)/10.0;
//	    System.out.println("Order ms:"+ordruntime[10]+" "+ordruntime[21]);
//	    System.out.println("Shuf ms:"+shufruntime[10]+" "+shufruntime[21]);
//	    System.out.println("Order:"+ordratio+" Shuf:"+shufratio);
	    
//	    double ordavg = (((double) ordruntime[21] / (double) ordruntime[10]) + 
//	    		((double) ordruntime[11]/ (double) ordruntime[5]))/2.0;
//	    double ordratio = Math.round(ordavg * 10)/10.0;
//	    double shufavg = (((double) shufruntime[21] / (double) shufruntime[10]) + 
//	    		((double) shufruntime[11]/ (double) shufruntime[5]))/2.0;
//	    double shufratio = Math.round(shufavg * 10)/10.0;
//	    System.out.println("Order ms:"+ordruntime[10]+" "+ordruntime[21]);
//	    System.out.println("Shuf ms:"+shufruntime[10]+" "+shufruntime[21]);
//	    System.out.println("Order:"+ordratio+" Shuf:"+shufratio);
	  }
}

*/

/*

public static void main(String[] args) {
	    MysterySorts srts = new MysterySorts();
	    int n;
	    int k = 20;
		int REP = 40;
//	    int k = 22;
//	    int REP = 70;

	    int[] ordruntime = new int[k];
	    int[] shufruntime = new int[k];
	    for (int i = 0; i < k; ++i) { 
	    	n = (i + 1) * 560;
	    	Integer[] A = new Integer[n];
//	    	Integer[] A = new Integer[n];
	    	for (int j = 0; j < n; ++j)
	    		A[j] = j;

	    	long ordstart = System.currentTimeMillis();
	    	for (int rep = 0; rep < REP; rep++) {
	    		srts.sort(0,A);
	    	}
	    	long ordend = System.currentTimeMillis();
	    	long ordtime = ordend - ordstart;
	    	ordruntime[i] = (int) ordtime;

	    	long shufstart = System.currentTimeMillis();
	    	for (int rep = 0; rep < REP; rep++) {
	    		srts.shuffleArray(A);
	    		srts.sort(0,A);
	    	}
	    	long shufend = System.currentTimeMillis();
	    	long shuftime = shufend - shufstart;
	    	shufruntime[i] = (int) shuftime;
	    }
	    Plot.view(ordruntime);
	    Plot.view(shufruntime);
//	     for(int i=0;i<k;++i)
//	     System.out.println(data[i]);
	    double ordavg = (((double) ordruntime[21] / (double) ordruntime[10]) + 
	    		((double) ordruntime[11]/ (double) ordruntime[5]))/2.0;
	    double ordratio = Math.round(ordavg * 10)/10.0;
	    double shufavg = (((double) shufruntime[21] / (double) shufruntime[10]) + 
	    		((double) shufruntime[11]/ (double) shufruntime[5]))/2.0;
	    double shufratio = Math.round(shufavg * 10)/10.0;
	    System.out.println("Order ms:"+ordruntime[10]+" "+ordruntime[21]);
	    System.out.println("Shuf ms:"+shufruntime[10]+" "+shufruntime[21]);
	    System.out.println("Order:"+ordratio+" Shuf:"+shufratio);
	  }


*/
/************************/


//public static void main(String[] args) {
//    MysterySorts srts = new MysterySorts();
//    int n;
//    int trials = 40;
//    int repeat = 15;
////    int k = 30; // experiment size
////    int REP = 10; // how many times to repeat experiment
//
//    int[] ordruntime = new int[trials];
//	int[] shufruntime = new int[trials];
//	try {
//		for (int i = 0; i < trials; i++) {
//			n = (i + 1) * 560;
//			Integer[] A = new Integer[n];
//
//			for (int nums = 0; nums < A.length; nums++)
//				A[nums] = nums;	
//
//			long ordstart = System.currentTimeMillis();
//
//			for (int rep = 0; rep < 5; rep++) {
//				srts.sort(1,A);
//			}
//
//			long ordend = System.currentTimeMillis();
//			long ordtime = ordend - ordstart;
//
//			ordruntime[i] = (int) ordtime;
//
//			long shufstart = System.currentTimeMillis();
//			for (int rep = 0; rep < 5; rep++) {
//				srts.shuffleArray(A);
//				srts.sort(1,A);
//			}
//			long shufend = System.currentTimeMillis();
//			long shuftime = shufend - shufstart;
//
//			shufruntime[i] = (int) shuftime;
//		}
//		
//		for (int i = 0; i < ordruntime.length; i++) 
//			System.out.println(ordruntime[i]);
//		System.out.println();
//		for (int i = 0; i < shufruntime.length; i++) 
//			System.out.println(shufruntime[i]);
//		
//		double ordratio = Math.round(((double) ordruntime[39]/ (double) ordruntime[19]) * 10)/10.0;
//		double shufavg = (((double) shufruntime[39] / (double) shufruntime[19]) + 
//				((double) shufruntime[19]/ (double) shufruntime[9]))/2.0;
//		double shufratio = Math.round(shufavg * 10)/10.0;
////		double shufratio = Math.round((((double) shufruntime[39]/ (double) shufruntime[19]) +
////				(double) shufruntime[39]/ (double) shufruntime[19])) * 10)/10.0;
//		System.out.println("Order ms:"+ordruntime[19]+" "+ordruntime[39]);
//		System.out.println("Shuf ms:"+shufruntime[19]+" "+shufruntime[39]);
//		System.out.println("Order:"+ordratio+" Shuf:"+shufratio);
//		System.out.println();
//		
//		if(ordratio >= 9)
//			ordratio = 0.0;
//		if(shufratio >= 9)
//			shufratio = 2.5;
//		if(ordratio >= 3.4 && shufratio >= 3.7)
//			System.out.println("selection");
////		else if ((ordratio <= 3.8 || ordratio == 0.0) && shufratio <= 2.7)
////			System.out.println("heap");
////		else if ((ordratio <= 3.2 || ordratio == 0.0) && shufratio <= 2.7)
////			System.out.println("heap");
//		else if((ordratio <= 2 || ordratio == 0.0) && (shufratio >= 3.3 && shufratio <= 4.2))
//			System.out.println("insertion");
//		else
//			System.out.println("heap");
//	}catch(StackOverflowError e) {
//		System.out.println("quick");
////		continue;
//	}
//  }   




    /************************/
    
//    int[] data = new int[k]; // to store run times
//    for (int i = 0; i < k; ++i) {
//      n = (i + 1) * 560;
//      Integer[] A = new Integer[n];
//      for (int j = 0; j < n; ++j)
//        A[j] = j;
//
//      long start = System.currentTimeMillis();
//      for (int rep = 0; rep < REP; rep++) {
//		srts.sort(3, A);
//	}
//    long now = System.currentTimeMillis();
//    long elapsed = now - start;
//    data[i] = (int) elapsed;
//    }
//     Plot.view(data);
//     for(int i=0;i<k;++i)
//     System.out.println(data[i]+" ms");
//  }
  
    /*******************************/
    
    
//  public static void main(String[] args) {
//	    MysterySorts srts = new MysterySorts();
//	    int n;
//	    int k = 15;
//	    int REP = 2;
//	    int k = 30; // experiment size
//	    int REP = 10; // how many times to repeat experiment
//
//	    int[] data = new int[k]; // to store run times
//	    for (int i = 0; i < k; ++i) { 
////	      n = (i + 1) * 5000;
//	      Integer[] A = new Integer[30];
//	      for (int j = 0; j < A.length; ++j)
//	        A[j] = j;
//
//	      long start = System.currentTimeMillis();
//	      srts.shuffleArray(A);
//	      srts.sort(2,A);
//	      long now = System.currentTimeMillis();
//	      long elapsed = now - start;
//	      data[i] = (int) elapsed;
//	    }
////	     Plot.view(data);
//	     for(int i=0;i<k;++i)
//	     System.out.println(data[i]+" ms");
//	  }

