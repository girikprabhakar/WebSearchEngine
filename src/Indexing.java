import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Indexing {
	public Map<Integer,String> source;
    public HashMap<String, HashSet<Integer>> index;
	
    Indexing(){
		prepareSearch();
	}
	
	//Populates the InvertedIndex database from files.
	public void prepareSearch() {
		source = new HashMap<Integer,String>();
        index = new HashMap<String, HashSet<Integer>>();
		File directory = new File("Webpages");
		File files[] = directory.listFiles();
		
		for(int i =0; i<files.length;i++) {
			
			String[] words = getStringFromFiles(files[i]);
			source.put(i,files[i].getName());
			createMap(words,i,files[i].getName());
		}
	}
	
	//Inserts provided value in the InvertedIndex database
	public void createMap(String[] words , int i, String fileName) {
		for(String word:words) {
			word = word.toLowerCase();
            if (!index.containsKey(word))
                index.put(word, new HashSet<Integer>());
            index.get(word).add(i);
		}
		
	}
	
	//Searches the database for the given word
	public ArrayList<String> find(String phrase){
    	ArrayList<String> fileNames;
    	try {
    		phrase = phrase.toLowerCase(); //to avoid case sensitivity, we transform whole text to lower case
    		fileNames = new ArrayList<String>(); // gets all file na,es
	        String[] words = phrase.split("\\W+");
	        String key = words[0].toLowerCase();
	        if(index.get(key) == null) {
	        	 System.out.println("Not found!");
	        	return null;
	        }
	        HashSet<Integer> res = new HashSet<Integer>(index.get(key));	        
	        for(String word: words){
	            res.retainAll(index.get(word));
	        }
	
	        if(res.size()==0) {
	            System.out.println("Not found!");
	            return null;
	        }
	        for(int num : res){
	        	fileNames.add(source.get(num));
	        }
    	}catch(Exception e) {
    		 System.out.println("Not found!");
    		 System.out.println("Exception Found:" + e.getMessage());
    		 return null;
    	}  
    return fileNames;
    }	
	
	//Returns words from given file
	public static String[] getStringFromFiles(File f) {
		try {
		In in = new In(f);
		String text = in.readAll();
		text = text.replaceAll("[^a-zA-Z0-9\\s]", ""); 
		String[] words = text.split(" ");		
		
		return words;
		}catch(Exception e) {
			return new String[0];
		}
		
	}
}
