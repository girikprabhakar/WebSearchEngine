import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.nodes.Document;

import textprocessing.In;
import textprocessing.TST;

public class AutoCompletor {
	TST<String> trie = new TST<String>();
	AutoCompletor(){
		String folderPath="Webpages/";
		String filename;
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			  filename=listOfFiles[i].getName();
			  System.out.println("Processed File:"+filename);
			  createTrie(folderPath+filename);
		  }
		}
	}
	void createTrie(String inputFilename) {
		String REGEX = "[\\[+\\]+:{}^~?\\\\/()><=\"!]'@";

//		StringUtils.replaceAll(inputString, REGEX, "\\\\$0");
		inputFilename = inputFilename.replaceAll(REGEX, "\\\\$0");
		In in = new In(inputFilename);
		
		ArrayList<String> tokens=new ArrayList<String>();
		
		Integer idx=0;
		String token;
		String indexes;
        while (!in.isEmpty()) {
            String s = in.readLine();
            StringTokenizer st=new StringTokenizer(s);
            while(st.hasMoreTokens()) {
            	token=st.nextToken();
    			indexes=trie.get(token);
				trie.put(token, idx.toString());
    			
    			idx+=(token.length()+1);
            }         
        }
	}

	ArrayList<String> getSuggestions(String token){
		ArrayList<String> suggestions=new ArrayList<String>();
		ArrayList<String> idx=trie.getIndexes(token);
		System.out.println();
		if(idx!=null) {
			System.out.println("\nTotal occurances'"+token+"':"+idx.size());
			System.out.println("Occurance offsets:"+idx);			
		}else {
			System.out.println("Total occurances of '"+token+"':0");
		}

		for(String suggestion:trie.prefixMatch(token)) {
			suggestions.add(suggestion);
		}
		return suggestions;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoCompletor ac=new AutoCompletor();
//		ac.searchTrie("Can");
	}

}
