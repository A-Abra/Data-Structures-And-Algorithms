import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class RestoreBlanks {

	public static ArrayList<String> wordList;
	//bin search for real words
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
	
	//method to break up words
	public static String phrase(String s) {
		//last word in phrase
		if(words(s))
			return s;
		for (int i = 0; i < s.length(); i++) {
			//if prefix is word
			if(words(s.substring(0,i))) {
				//store suffix (rest of phrase) in str
				String str = phrase(s.substring(i));
				//create chain of words
				if(str != null)
					return s.substring(0,i)+" "+str;
				//if(phrase(s.substring(i)) != null)
					//return s.substring(0,i)+" "+phrase(s.substring(i));;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		wordList = new ArrayList<String>();
		File file = new File("words.txt");
		//File file = new File("../resource/asnlib/public/words.txt");
		
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
		final long start=System.currentTimeMillis();
		System.out.println(phrase(s));
		final long end=System.currentTimeMillis();
		System.out.println(end-start+"millisec");
	}
}