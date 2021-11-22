import java.io.File;
import java.util.*;
import java.util.Map.Entry;

public class Search {
	//Searches for a phrase in the files using InvertedIndex created by Indexing.java.
	public static void searchPhrase(String keyword,int numberOfResults) {
		Indexing index = new Indexing();
		String phrase = keyword;
		ArrayList<String> wordSearch = index.find(phrase);
		phrase = phrase.toLowerCase();
		if(wordSearch==null) {			
			// Search Simillar words;
		}
		else {		
			Map<String,Integer> sortedMap = SortResultsByRank.sortingByRank(wordSearch, phrase);
			printResult(sortedMap,numberOfResults);
		}
	}
	
	//Prints given number of results from the provided Map.
	public static void printResult(Map<String,Integer> result, int numberOfResults) {
		Iterator<Entry<String, Integer>> iterator = result.entrySet().iterator();  
		int i = 0;
		while(iterator.hasNext() && numberOfResults>i++)   
		{  			
			Map.Entry<String, Integer> me = (Map.Entry<String, Integer>)iterator.next();
			String fileName = me.getKey().toString();			
			File f = new File("Webpages/"+fileName);
			In in = new In(f);
			String url = in.readLine();			
			System.out.println("-----------------------------------------");
			System.out.println(fileName.substring(0,fileName.length()-4)+"\t\tOccurrences : "+me.getValue()); //Occurrences 
			System.out.println(url);  
		}
	}
}
