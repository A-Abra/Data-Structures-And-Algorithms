import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * Print out all dictionary records in a given range of keys.
 */
public class PrintRange {

  public static void main(String[] args) {
    BST<String, Pronunciation> PDict = new BST<String, Pronunciation>();
    File file = new File("shuffledDictionary.txt");

    // Read in the (shuffled) cmu pronunciation dictionary.
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.substring(0, 3).equals(";;;"))
          continue; // skip comment lines
        Pronunciation p = new Pronunciation(line);
        PDict.insert(p.getWord(), p); // key dictionary on word
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    Scanner input = new Scanner(System.in);
    String sc = input.next();
    System.out.println(PDict.smallCount(sc).size());
    
    
    String a = input.next(); // first word in range
    String b = input.next(); // last word in range
    for(Pronunciation p : PDict.range(a,b)) //go through words between a and b (including a and b)
      System.out.println(p.getWord()+" "+p.getPhonemes());
  }
}
