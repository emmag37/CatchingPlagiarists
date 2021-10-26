import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Write a description of class CatchingPlagiarists here.
 *
 * @author Emma Glenn
 * @version 3/17/2021
 */
public class CatchingPlagiarists
{
    // Instance Vars
    private String directory; // directory entered
    private ArrayList<String> files; // list of file names
    private ArrayList<ArrayList<String>> filePhrases; // list of phrases in files from the directory
    private int threshhold;
    private int n;

    // Constructor - makes an an object representing the list of files to be checked and their phrases
    public CatchingPlagiarists(String d, int t, int p) throws FileNotFoundException
    {
        threshhold = t;
        n = p;
        directory = d;
    }

    public void catchPlagiarists() throws FileNotFoundException // produces and returns the hit-list
    {
        // Makes the hits list as a list of FilePair objects
        ArrayList<FilePair> hitList = new ArrayList<FilePair>();

        // Get all of the files in a given directory

        System.out.println("Getting files...");

        File dir = new File(directory);
        String[] temp = dir.list();
        files = new ArrayList<String>();
        filePhrases = new ArrayList<ArrayList<String>>();
        int numFiles = temp.length;

        for (int i = 0; i < numFiles; i++)
        {
            if (i == numFiles/4)
                System.out.println("25% done!");
            if (i == numFiles/2)
                System.out.println("50% done!");
            if (i == numFiles/4*3)
                System.out.println("75% done!");

            if (temp[i].endsWith(".txt"))
            {
                files.add(temp[i]);
                filePhrases.add(listPhrases(temp[i]));
                // compare new file to previous ones, add to hitlist
                if (i > 0)
                {
                    for (int k = 0; k < files.size() - 1; k++)
                    {
                        int hits = commonPhrases(i, k);
                        if (hits != -1)
                            hitList.add(new FilePair(files.get(k), files.get(i), hits));
                    }
                }

            }
        }

        // Sort the list
        Collections.sort(hitList, new SortPair());

        System.out.print(hitList.toString());
    }
    // Given a file and an n, produces a list of the n-word phrases in the file
    private ArrayList<String> listPhrases(String fileName) throws FileNotFoundException
    {
        ArrayList<String> phrases = new ArrayList<String>();
        ArrayList<String> createPhrases = new ArrayList<String>();

        File f = new File(directory + "/" + fileName);
        Scanner file = new Scanner(f);

        // create the list of phrases
        int currIndex = 0; // index of the current word being added to the phrases
        int endIndex = -1; // index of the phrase being finished with the current word
        while (file.hasNext())
        {
            // get the word
            String word = file.next().replaceAll("[^A-z]","").toLowerCase();
            if (!word.equals(""))
            {
                // add the start of the phrase
                createPhrases.add(word);

                // find the end index
                if (currIndex >= n)
                    endIndex = currIndex - n;

                // add the word to all necessary phrases
                for (int i = currIndex - 1; i > endIndex; i--)
                {
                    String phrase = createPhrases.get(i);
                    phrase += word;
                    createPhrases.set(i, phrase);
                }
                
                if (currIndex >= n - 1)
                {
                    String finalPhrase = createPhrases.get(endIndex + 1);
                    phrases.add(finalPhrase);
                }
                currIndex++;
            }

        }

        // removes phrases with < n words
        int size = phrases.size();
        for (int i = 1; i < n; i++)
            phrases.remove(size - i);

        return phrases;
    }
    // Given two lists of phrases, returns the number of phrases they have in common
    // Returns -1 if the number of common phrases is below the threshold
    private int commonPhrases(int file1, int file2) throws FileNotFoundException
    {
        int commonPhrases = 0;

        ArrayList<String> list1 = filePhrases.get(file1);
        ArrayList<String> list2 = filePhrases.get(file2);
        // Loop through all of the phrases in file1 to compare to each phrase in file2
        for (int i = 0; i < list1.size(); i++)
        {
            for (int j = 0; j < list2.size(); j++)
            {
                if (list1.get(i).equals(list2.get(j)))
                    commonPhrases++;
            }
        }

        if (commonPhrases < threshhold)
            return -1;

        return commonPhrases;
    }
}
