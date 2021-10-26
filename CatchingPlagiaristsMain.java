import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
/**
 * Write a description of class CatchingPlagiaristsMain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CatchingPlagiaristsMain
{
    // Main method
    public static void main(String[] args) throws FileNotFoundException
    {
        // User interface
        System.out.println("Catching Plagiarists");
        System.out.println("\nThis program returns the number of common phrases between documents.");
        System.out.println("Given a set of documents, the number of words in each phrase, and a cutoff threshold for a document ");
        System.out.println("to count as plagiarism, the list of plagiarized documents will be returned in order of most to least ");
        System.out.println("plagiarized. The menu below indicates the corresponding value to each document list.");
        System.out.println("\nSmall set -> 0\nMedium set -> 1");

        // Enter values - validate
        Scanner userInput = new Scanner(System.in);
        
        // Choose the list -- must enter a 0 or 1
        boolean listChosen = false;
        int list1 = 0;
        while (!listChosen)
        {
            System.out.print("\nChoose your list of documents: ");
            while (!userInput.hasNextInt())
            {
                System.out.println("Please enter a 0 or 1");
                userInput.next();
            }
            list1 = userInput.nextInt();
            if (list1 == 0 || list1 == 1 || list1 == 2)
                listChosen = true;
        }
        int list = list1;
        System.out.println("");
        // Enter number of words per phrase -- not a negative number, 10 or less, greater than 0
        boolean numEntered = false;
        int numWords1 = 0;
        while (!numEntered)
        {
            System.out.println("\nEnter number of words in phrase: ");
            while (!userInput.hasNextInt())
            {
                System.out.println("Please enter an integer");
                userInput.next();
            }
            numWords1 = userInput.nextInt();
            if (numWords1 > 0 && numWords1 <= 10)
                numEntered = true;
        }
        int numWords = numWords1;
        System.out.println("");
        // Enter the threshold -- greater than 0, 100 or less?
        boolean threshEntered = false;
        int thresh1 = 0;
        while (!threshEntered)
        {
            System.out.println("Enter threshold value: ");
            while (!userInput.hasNextInt())
            {
                System.out.println("Please enter an integer");
                userInput.next();
            }
            thresh1 = userInput.nextInt();
            if (thresh1 > 0 && thresh1 < 100)
                threshEntered = true;
        }
        int thresh = thresh1;
        System.out.println("");

        System.out.println("\nFinding plagiarists...");
        
        // Scan in the directories
        File dir = new File(".");
        ArrayList<File> directories = new ArrayList<File>();
        for(File folder : dir.listFiles())
            if(folder.isDirectory())
                directories.add(folder);
        

        // Run catching plagiarists methods and return hit list
        CatchingPlagiarists toCatch = new CatchingPlagiarists(directories.get(list).toString(), thresh, numWords);
        toCatch.catchPlagiarists();

    }
}
