
/*
 * CITS3001 Lab 4
 * Implementation of Word Chess problem
 * Student name: Hoang Tuan Anh
 * Student ID: 21749914 
 * The University of Western Australia
 * Resources used in this lab:
 * https://stackoverflow.com/questions/1090556/java-how-to-convert-hashmapstring-object-to-array
 * https://stackoverflow.com/questions/7655127/how-to-convert-a-char-array-back-to-a-string
 * CITS2200 Lecture 12 Tree Traversals: http://teaching.csse.uwa.edu.au/units/CITS2200/Lectures/Topic12-Traversals.pdf
 * CITS3001 Lecture 05 Uninformed search: http://teaching.csse.uwa.edu.au/units/CITS3001/lectures/lectures/05UninformedSearch.pdf
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;

public class WordChessImp implements WordChess {

	//Private function: check if a word is exactly one character different to another word
	private boolean oneCharDifferent(String first, String second)
	{
		if(first.length() != second.length())
		{
			return false;
		}
		int numDifference = 0;
		char[] temp1 = first.toCharArray();
		char[] temp2 = second.toCharArray();
		
		for(int i = 0; i < first.length(); i++)
		{
			if(temp1[i] != temp2[i])
			{
				numDifference++;
				if(numDifference > 1)
				{
					return false;
				}
			}
		}
		return numDifference == 1;
	}

	@Override
	public String[] findPath(String[] dictionary, String startWord, String endWord) {
		//Lab 4

		HashSet<String> visited = new HashSet<String>();
		HashMap<String, String> parents = new LinkedHashMap<String, String>();

		HashSet<String> listDictionary = new HashSet<String>();

		for (int i = 0; i < dictionary.length; i++) {
			listDictionary.add(dictionary[i].trim());
		}
		/* Convert dictionary array to list.
		 * For easier lookup.
		 */
		ArrayDeque<String> q = new ArrayDeque<String>();

		q.add(startWord);
		visited.add(startWord);
		boolean found = false;

		//Perform BFS
		while (!found && !q.isEmpty()) 
		{
			String currentWord = q.pop();
			for (int i = 0; i < currentWord.length(); i++) 
			{
				char[] temp = currentWord.toCharArray();
				for(int j = 0; j < 25; j++)
				{
					temp[i] = (char)((temp[i] - 'A' + 1) % 26 + 'A');
					String newWord = new String(temp);
					if (listDictionary.contains(newWord) && oneCharDifferent(currentWord, newWord)) {
						if (!visited.contains(newWord)) {
							parents.put(newWord, currentWord);
							q.add(newWord);
							visited.add(newWord);
							if (newWord.equals(endWord)) {
								found = true;
								break;
							}
						}
					}
				}
			}
			visited.add(currentWord);
		}

		//Convert parents hash map to array to return the path
		ArrayList<String> result = new ArrayList<>();
		String key = endWord;
		result.add(key);
		while(parents.containsKey(key))
		{
			key = parents.get(key);
			result.add(key);
		}
		
		Collections.reverse(result);
		return result.toArray(new String[0]);
	}
	
	//This is for Lab 5, please ignore this section.
	public String[] findPath2(String[] dictionary, String startWord, String endWord) {
		// TODO Auto-generated method stub
		//Lab 5: improve the performance of Lab 4 with heuristic
		
		HashSet<String> visited = new HashSet<String>();
		HashMap<String, String> parents = new LinkedHashMap<String, String>();

		HashSet<String> listDictionary = new HashSet<String>();

		for (int i = 0; i < dictionary.length; i++) {
			listDictionary.add(dictionary[i].trim());
		}
		
		Comparator<String> comparator = new MatchingComparator(endWord);
		PriorityQueue<String> q = new PriorityQueue<String>(dictionary.length, comparator);
		/*
		 * Note: Java version before 8 requires 
		 * a queue size when calling 
		 * PriorityQueue constructor
		 * with a custom Comparator.
		 */
		ArrayDeque<String> q2 = new ArrayDeque<String>();

		q.add(startWord);
		visited.add(startWord);
		boolean found = false;

		while (!found && !q.isEmpty()) 
		{
			String currentWord = q.poll();
			for (int i = 0; i < currentWord.length(); i++) 
			{
				char[] temp = currentWord.toCharArray();
				for(int j = 0; j < 25; j++)
				{
					temp[i] = (char)((temp[i] - 'A' + 1) % 26 + 'A');
					String newWord = new String(temp);
					if (listDictionary.contains(newWord) && oneCharDifferent(currentWord, newWord)) 
					{
						if (!visited.contains(newWord)) 
						{
							parents.put(newWord, currentWord);
							q2.add(newWord);
							visited.add(newWord);
							if (newWord.equals(endWord)) 
							{
								found = true;
								break;
							}
						}
					}
				}
			}
			visited.add(currentWord);
			if(q.isEmpty())
			{
				q.addAll(q2);
				q2.removeAll(q2);
			}
		}

		ArrayList<String> result = new ArrayList<>();
		String key = endWord;
		result.add(key);
		while(parents.containsKey(key))
		{
			key = parents.get(key);
			result.add(key);
		}
		
		Collections.reverse(result);
		return result.toArray(new String[0]);
	}
	
	private int numMatchingChar(String first, String second)
	{
		if(first.length() != second.length())
		{
			return -1;
		}
		int numMatching = 0;
		char[] temp1 = first.toCharArray();
		char[] temp2 = second.toCharArray();
		
		for(int i = 0; i < first.length(); i++)
		{
			if(temp1[i] == temp2[i])
			{
				numMatching++;
			}
		}
		return numMatching;
	}
	
	public class MatchingComparator implements Comparator<String>
	{
		String targetWord = new String();
		@Override
		public int compare(String a, String b) {
			// TODO Auto-generated method stub
			if(numMatchingChar(a, targetWord) > numMatchingChar(b, targetWord))
			{
				return -1;
			}
			if(numMatchingChar(a, targetWord) < numMatchingChar(b, targetWord))
			{
				return 1;
			}
			return 0;
		}
		
		public MatchingComparator(String c)
		{
			this.targetWord = c;
		}

	}
	
}

