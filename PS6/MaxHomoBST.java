import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MaxHomoBST {

	public static void main(String[] args) {
		//BST of Phonemes with arr of words
		BST<String, ArrayList<String>> PDict = new BST<String, ArrayList<String>>();
		File file = new File("cmudict.0.7a.txt");
		
		int max = 0;
		
		Scanner input = new Scanner(System.in);
		int lines = input.nextInt();
		final long start=System.currentTimeMillis();
		if(lines > 133370)
			lines = 133370;
		
		try {
			Scanner scanner = new Scanner(file);
			//go up to num lines inputted
			for(int lineNum = 1; lineNum <= lines; lineNum++) {
				if(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (line.substring(0, 3).equals(";;;"))
						continue; // skip comment lines
					Pronunciation p = new Pronunciation(line);
					
					ArrayList<String> ls = PDict.find(p.getPhonemes());
					//if phoneme found in dict
					if(ls != null) {
						//add to arrlist
						ls.add(p.getWord());
						//update arrsize
						if(max < ls.size())
							max = ls.size();
					}
					//else add new arrlist with new phoneme and word
					else {
						if(p.getWord().length() == 7) {
							ArrayList<String> nList = new ArrayList<String>();
							nList.add(p.getWord());
							PDict.insert(p.getPhonemes(), nList);
						}
//						ArrayList<String> nList = new ArrayList<String>();
//						nList.add(p.getWord());
//						PDict.insert(p.getPhonemes(), nList);
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    }
		System.out.println(max);
		int count = 0;
		for (ArrayList<String> set : PDict.values()) {
			//go through bst to find biggest arrlist(s)
			//and print contents
//			if(set.size() == max) {
//				for (int i = 0; i < set.size(); i++)
//					System.out.println(set.get(i));
//				System.out.println();
//			}
			
			for (int i = 0; i < set.size(); i++) {
				System.out.println(set.get(i));
				count++;
			}
				
			System.out.println();
		}
		System.out.println(count);
		final long end=System.currentTimeMillis();
		long total=end-start;
		System.out.println(total+"millisec");
	}
}
