import java.util.*;
/**
 * Write a description of class FilePair here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FilePair
{
    // instance variables
    private String fileA;
    private String fileB;
    private int hits;
    
    public FilePair(String a, String b)
    {
        fileA = a;
        fileB = b;
        
        hits = 0;
    }
    public FilePair(String a, String b, int h)
    {
        fileA = a;
        fileB = b;
        
        hits = h;
    }
    
    public int getHits()
    {
        return hits;
    }
    
    public void setHits(int h)
    {
        hits = h;
    }
    
    public String toString()
    {
        return "\n[" + fileA + ", " + fileB + "] -> " + hits;
    }
    
}