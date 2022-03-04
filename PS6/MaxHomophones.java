import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MaxHomophones {

	public static void main(String[] args) {
		UALDictionary<String, ArrayList<String>> PDict = new UALDictionary<String, ArrayList<String>>();
		File file = new File("cmudict.0.7a.txt");
		int max = 0;
		
		Scanner input = new Scanner(System.in);
		int lines = input.nextInt();
		final long start=System.currentTimeMillis();
		//safety if input goes over limit
		if(lines > 133370)
			lines = 133370;
		
		try {
			Scanner scanner = new Scanner(file);
			//go up to num lines inputted
			for(int lineNum = 1; lineNum <= lines; lineNum++) {
				if(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (line.substring(0, 3).equals(";;;"))
						continue;
					Pronunciation p = new Pronunciation(line);
					ArrayList<String> ls = PDict.find(p.getPhonemes()); //find the key in dict which phoneme is the key
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
						ArrayList<String> nList = new ArrayList<String>();
						nList.add(p.getWord());
						PDict.insert(p.getPhonemes(), nList);
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(max);
		
		for (ArrayList<String> set : PDict.values()) {
			//go through dict to find biggest arrlist
			//and print contents
			if(set.size() == max) {
				for (int i = 0; i < set.size(); i++)
					System.out.println(set.get(i));
				System.out.println();
			}
		}
		final long end=System.currentTimeMillis();
		long total=end-start;
		System.out.println(total+"millisec");
	}
}
