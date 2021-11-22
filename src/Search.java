import java.io.File;
import java.net.URI;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import textprocessing.In;

public class Search {
	/**
	 * This function Searches for a phrase in the files using InvertedIndex created by Indexing.java.
	 * @param keyword String to be searched
	 * @param numberOfResults Total number of matching results
	 * @return Arraylist containing the result
	 */
	public static ArrayList<HashMap<String, String>> searchPhrase(String keyword,int numberOfResults) {
		Indexing index = new Indexing();
		String phrase = keyword;
		ArrayList<String> wordSearch = index.find(phrase);
		phrase = phrase.toLowerCase();
		if(wordSearch==null) {			
			// Search Simillar words;
		}
		else {		
			Map<String,Integer> sortedMap = SortResultsByRank.sortingByRank(wordSearch, phrase);
			return printResult(sortedMap,numberOfResults,keyword);
		}
		return new ArrayList<HashMap<String, String>>();
	}
	
	/**
	 * Prints given number of results from the provided Map.
	 * @param result Map containing the search results in sorted manner
	 * @param numberOfResults Total number of matching results
	 * @param keyword String that was searched
	 * @return The arraylist of the consolidated result
	 */
	public static ArrayList<HashMap<String, String>> printResult(Map<String,Integer> result, int numberOfResults,String keyword) {
		Iterator<Entry<String, Integer>> iterator = result.entrySet().iterator();  
		int i = 0;
		ArrayList<HashMap<String,String>> resultCollection=new ArrayList<HashMap<String,String>>();
		while(iterator.hasNext() && numberOfResults>i++)   
		{  			
			Map.Entry<String, Integer> me = (Map.Entry<String, Integer>)iterator.next();
			String fileName = me.getKey().toString();			
			File f = new File("Webpages/"+fileName);
			In in = new In(f);
			String description = in.readLine();	
			int start;
			int end;
			int keywordIdx=description.toLowerCase().indexOf(keyword.toLowerCase());
			int offset=100;
			if(keywordIdx<offset) {
				start=0;
			}else {
				start=keywordIdx-offset;
			}
			if(keywordIdx+offset>description.length()) {
				end=description.length();
			}else {
				end=keywordIdx+offset;
			}
			if(keywordIdx==-1) {
				start=0;
				end=Math.min(200, description.length());
			}
			description=description.substring(start,end);
	        Pattern patrn = Pattern.compile("(?i)"+keyword);
	        
	        // All metched patrn from str case
	        // insensitive or case sensitive
	        Matcher match = patrn.matcher(description);
	  
	        while (match.find()) {
	            // Print matched Patterns
	            System.out.println(match.group());
	            description="<html>"+description.replaceAll(match.group(), "<span style=\"background-color:yellow;\">"+match.group()+"</span>")+"</html>";
	        }
			
			System.out.println("-----------------------------------------");
			System.out.println(fileName.substring(0,fileName.length()-4)+"\t\tOccurrences : "+me.getValue()); //Occurrences 
			System.out.println(getLink(fileName));
			System.out.println(description);  
			HashMap<String,String> res=new HashMap<String,String>();
			res.put("title",fileName.substring(0,fileName.length()-4)+"\t\tOccurrences : "+me.getValue() );
			res.put("link", getLink(fileName));
			res.put("description",description);
			resultCollection.add(res);
		}
		return resultCollection;
	}
	
	/**
	 * This function generates link from the title of the file.
	 * @param title Title of the file
	 * @return generated link
	 */
	public static String getLink(String title) {
		File file = new File("UrlToTitle.txt");
		URI uri = file.toURI();
		String asciiURI = uri.toASCIIString();
		In in = new In(asciiURI);
		String link="";
        while (!in.isEmpty()) {
        	String s= in.readLine();
        	if(s.indexOf(title.replace(".txt", ""))>=0) {
        		String[] parts=s.split("///");
        		link= parts[1];
        		break;
        	}
        }
		return link;
	}
	public static void main(String[] args) {
		Search.searchPhrase("Member", 10);
	}
	
}
