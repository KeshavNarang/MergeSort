import java.io.*;
import java.util.*;
public class MergeSort
{
	static LinkedList <Character> word = new LinkedList <Character> (); // Linked List (MERGESORTEXAMPLE)
	
	public static void main (String [] args) throws IOException
	{		
		word.add('M');
		word.add('E');
		word.add('R');
		word.add('G');
		word.add('E');
		word.add('S');
		word.add('O');
		word.add('R');
		word.add('T');
		word.add('E');
		word.add('X');
		word.add('A');
		word.add('M');
		word.add('P');
		word.add('L');
		word.add('E');
		
		
		System.out.println(word); 

		int startFirstSequence = 0; 
		int startSecondSequence = 0;
		int startThirdSequence = 0; 
		
		// The list is sorted when the end of the first ascending sequence is the end of the word. 
		while (startSecondSequence < (word.size() - 1))
		{
			startFirstSequence = 0; 
			startSecondSequence = 0;
			startThirdSequence = 0;
			
			// When the end of the second ascending sequence is the end of the word, go back and remerge everything.
			while (startThirdSequence < (word.size() - 1))
			{
				startFirstSequence = startThirdSequence;
				startSecondSequence = findNextStart(startFirstSequence);
				startThirdSequence = findNextStart(startSecondSequence);
				merge(startFirstSequence, startSecondSequence, startThirdSequence);	
			}
			System.out.println(word);
		}
	}
	
	// Returns the index of the last element in the ascending sequence starting at startIndex
	public static int findNextStart (int startIndex)
	{
		// Keep checking if the element at the current index is less than the next element
		int endIndex = startIndex;
		while ((endIndex < (word.size() - 1)) && (word.get(endIndex).compareTo(word.get(endIndex+1)) <= 0))
		{
			endIndex++;
		}
		return endIndex + 1;
	}
	
	// Run 1: startIndex1 --> startIndex2 - 1 and Run 2: startIndex2 --> startIndex3
	public static void merge (int startIndex1, int startIndex2, int startIndex3) 
	{
		int currentIndex = startIndex1; // Where to add the next element
		int i = startIndex1; // Index of next element in run 1 to be sorted (shifts as elements are sorted)
		int j = startIndex2; // Index of next element in run 2 to be sorted (shifts as elements are sorted)
		int pos1 = startIndex1; // Every time a new element in run 1 is sorted, pos1 increases. when pos1 = startIndex2, run1 is sorted
		int pos2 = startIndex2; // Every time a new element in run 2 is sorted, pos2 increases. when pos2 = startIndex3, run2 is sorted
		
		while ((pos1 < startIndex2) && (pos2 < startIndex3)) // Until either run is sorted
		{
			if ((word.get(i).compareTo(word.get(j))) < 0) // If run1's current element is before run2's current element
			{	
				char add = word.get(i); 
				word.remove(i); // remove run1's current element
				word.add(currentIndex, add); // add it to the proper position
				pos1++; // run1 is now one element less from being sorted
				i++; // the position of the next element in run1 has been shifted as a result of adding an element before it
			}
			else
			{
				char add = word.get(j);
				word.remove(j);
				word.add(currentIndex, add);
				pos2++;
				i++; // Adding an element from the second run before the element from the first run shifts it
				j++; // The whole list between the current position and the former position has been shifted by one
			}
			currentIndex++;
		}
	}
}