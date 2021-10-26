import java.util.*;
/**
 * Write a description of class SortPair here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SortPair implements Comparator<FilePair>
{
    public int compare(FilePair pair1, FilePair pair2)
    {
        return pair2.getHits() - pair1.getHits();
    }
}
