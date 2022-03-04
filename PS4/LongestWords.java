import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class LongestWords {
	public static ArrayList<String> wordList;
	public static ArrayList<String> validWords = new ArrayList<String>();

	//ins sort
	public static ArrayList<String> insSort(ArrayList<String> arr) {
		for (int i = 1; i < arr.size(); i++) {
			String next = arr.get(i);
			int j=i;
			while(j>0 && arr.get(j-1).compareTo(next) > 0) {
				arr.set(j, arr.get(j-1));
				j--;
			}
			arr.set(j, next);
		}
		return arr;
	}
	
	//bin search
	public static boolean words(String someWord) {
		int l = 0, r = wordList.size()-1;
		while(l <= r) {
			int mid = l+(r-l)/2;
			if(someWord.equals(wordList.get(mid)) || someWord.equals(wordList.get(r)) || someWord.equals(wordList.get(l)))
				return true;
			else if(someWord.compareTo(wordList.get(mid)) < 0)
				r = mid - 1;
			else
				l = mid + 1;
		}
		return false;
	}
	
	//gets all permutations for a string
	public static ArrayList<String> perm(String s){
		ArrayList<String> results = new ArrayList<String>();
		perm("",s,results);
		return results;
	}
	public static void perm(String pre, String suff, ArrayList<String> variations) {
		if(suff.length()==0)
			variations.add(pre);
		else {
			for (int i = 0; i < suff.length(); i++) {
				perm(pre+suff.charAt(i),suff.substring(0,i)+suff.substring(i+1,suff.length()),variations);
			}
		}
	}
	
	public static void main(String[] args) {
		wordList = new ArrayList<String>();
		File file = new File("words.txt");
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.next();
				wordList.add(line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		
		/*
		String str = "boat";
		ArrayList<String> test = perm(str);
		for (int i = 0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		*/
		
		
		Deque<CState> q = new LinkedList<CState>();
		final long strtTime=System.currentTimeMillis();
		CState start = new CState("", s);
		q.addFirst(start);
		int maxLen = 0;
		while (!q.isEmpty()) {
			CState next = q.removeFirst();
			if (next.suff().length() > 0) {
				//feed in prefix to get permutations
				String newPrefix = next.pre() + next.suff().charAt(0);
				String newSuffix = next.suff().substring(1);
				ArrayList<String> arr = perm(newPrefix);
				//go through list of perms to find words and update maxlen
				for(int i=0; i < arr.size(); i++){
					//words formed equal to or bigger than max len
					//to funnel small words
					//if its a word and the max len is smaller than word len, update
//					if(words(arr.get(i)) && maxLen <= arr.get(i).length()){
//						validWords.add(arr.get(i));
//						maxLen=arr.get(i).length();
//					}
					
					//words formed for any length
					if(words(arr.get(i))){
						validWords.add(arr.get(i));
					}
				}
				CState n1 = new CState(newPrefix, newSuffix);
				CState n2 = new CState(next.pre(), newSuffix);
				q.addFirst(n1);
				q.addFirst(n2);
				//q.addLast(n2);
				//q.addLast(n1);
			}
		}
		
		//sort list
//		insSort(validWords);
		Collections.sort(validWords);
		
		/*
		for (int i = 0; i < validWords.size(); i++) {
			for (int j = 0; j < validWords.size(); j++) {
				if(validWords.get(i).equals(validWords.get(j)) && i != j)
					validWords.remove(i);
			}
		}
		*/
		//prints biggest words
//		for (int i = 0; i < validWords.size(); i++) {
//			if(maxLen==validWords.get(i).length())
//				System.out.println(validWords.get(i));
//		}
		
		//prints all valid words
		for (int i = 0; i < validWords.size(); i++) {
			System.out.println(validWords.get(i));
		}
		System.out.println(validWords.size());
		final long endTime=System.currentTimeMillis();
		System.out.println(endTime-strtTime+"millisec");
	}
}