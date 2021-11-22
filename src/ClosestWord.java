import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.sound.midi.Sequence;

import textprocessing.In;
import textprocessing.Sequences;
public class ClosestWord {
	static ArrayList<String> tokens;
	ClosestWord(){
		String folderPath="Webpages/";
		String filename;
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		tokens= new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			  filename=listOfFiles[i].getName();
			  System.out.println("Processed File:"+filename);
			  String inputFilename =folderPath+filename;
				File file = new File(inputFilename);
				URI uri = file.toURI();
				String asciiURI = uri.toASCIIString();
				In in = new In(asciiURI);
				Integer idx=0;
				String token;
				String indexes;
		        while (!in.isEmpty()) {
		            String s = in.readLine();
		            StringTokenizer st=new StringTokenizer(s);
		            while(st.hasMoreTokens()) {
		            	token=st.nextToken();
		            	tokens.add(token);
		            }
		        }
		        
		  }
		}
	}
	
	public static String closetWordFrom (String s) {
		int minDist=Integer.MAX_VALUE;
		String nearest="";
		for (String token : tokens ) {
			int tempDist=Sequences.editDistance(token, s);
			if(minDist>tempDist) {
				minDist=tempDist;
				nearest=token;
			}
		}
		System.out.println(minDist);
		return nearest;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClosestWord ac=new ClosestWord ();
		System.out.println(ac.closetWordFrom("addditioon"));
	}

}
