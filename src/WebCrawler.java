import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 */

/**
 * 
 *
 */
public class WebCrawler {

	/**
	 * This function returns the links of all the sublinks present in the link.
	 * @param link to be crawled
	 * @return String containing all the sublinks present in the link.
	 */
	static HashSet<String> alreadyCrawled=new HashSet<String>();
	public static String crawlSublinks(String link) {
		if(alreadyCrawled.contains(link)) {
			return "";
		}else {
			alreadyCrawled.add(link);
		}
		Document doc = htmlToText(link);
		String links = "";
		if (doc != null) {
			String text = doc.text();
			String title = doc.title();
			Elements es = doc.select("a");
			
			for(Element e : es) {
				String href = e.attr("abs:href");
				if(href.length()>3)
				{
					links = links+"\n"+href;
				}
			}
		}
		return links;
	}
	
	/**
	 * This function converts the HTML content of the link to the text format and write it to the file with it's title name under Webpages folder
	 * @param link to be converted
	 * @return Document Variable that has the information of the connected link
	 */
	public static Document htmlToText(String link) {
		Document doc;
		try {
			doc = Jsoup.connect(link).get();
			String title = doc.title().replace("|","_").replace("?", "_").replace("\\","_"); 
			BufferedWriter writer = new BufferedWriter(new FileWriter("Webpages/"+ title + ".txt"));
			writer.write(doc.text());
			writer.close();
			
			File f = file_create("UrlToTitle.txt");
			FileWriter fw;
			try {
				fw = new FileWriter(f,true);
				fw.write(title+"///"+link + "\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return doc;
			
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("error in connecting to the page " + link + "\n\n");;
			return null;
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("error in connecting to the page " + link + "\n\n");			
			return null;
		}
	}
	
	/**
	 * This function crawls the link provided by the user.
	 * @param link To be crawled
	 */
	public static void crawlPrimaryLink(String link) {
		writeToCrawledPage(link);
		System.out.println(link);
		String l2Links = crawlSublinks(link);
		l2Crawling(l2Links);
	}
	
	
	/**
	 * This functions performs the level 2 crawling
	 * @param l2_links contains all the level 2 links
	 */
	public static void l2Crawling(String l2Links) {
		String l3Links = "";
		String [] arrayL2Links = l2Links.split("\n");
		for (String l2Link : arrayL2Links) {
			if(!l2Link.isEmpty())
			{
				writeToCrawledPage(l2Link);
				System.out.println(l2Link);
				l3Links = l3Links + crawlSublinks(l2Link);
			}
		}
		if(!l3Links.isEmpty())l3Crawling(l3Links);
	}
	
	/**
	 * This functions performs the level 3 crawling
	 * @param l3_links contains all the level 3 links
	 */
	public static void l3Crawling(String l3Links) {
		String l4Links = "";
		String [] arrayL3Links = l3Links.split("\n");
		for (String l3Link : arrayL3Links) {
			if(!l3Link.isEmpty())
			{
				writeToCrawledPage(l3Link);
				System.out.println(l3Link);
				l4Links = l4Links +  crawlSublinks(l3Link);
			}
		}
		if(!l4Links.isEmpty())l4Crawling(l4Links);
	}
	
	/**
	 * This functions performs the level 4 crawling
	 * @param l4_links contains all the level 4 links
	 */
	public static void l4Crawling(String l4Links) {
		String [] array_l4_links = l4Links.split("\n");
		for (String l4_link : array_l4_links) {
			if(!l4_link.isEmpty())
			{
				writeToCrawledPage(l4_link);
				System.out.println(l4_link);
			}
		}
	}
	
	/**
	 * This function creates file if it doesn't exist
	 * @param name Name of the file to be created
	 * @return File created
	 */
	public static File file_create(String name) {
		File f = new File(name);
		try {
			if(!f.exists()){
				  f.createNewFile();
				}
			return f;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * This function keeps the record of the crawled link in the crawledlinks.txt file
	 * @param link that has been crawled
	 */
	public static void writeToCrawledPage(String link) {
		File f = file_create("crawledlinks.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(f,true);
			fw.write(link + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Enter link to be crawled including https: ");
		String string_link = myObj.nextLine();  // Read user input
		crawlPrimaryLink(string_link);
	}

}
