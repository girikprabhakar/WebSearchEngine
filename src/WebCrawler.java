import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 */

/**
 * @author prabh
 *
 */
public class WebCrawler {

	/**
	 * This function returns the links of all the sublinks present in the link.
	 * @param link to be crawled
	 * @return String containing all the sublinks present in the link.
	 */
	public static String crawl_sublinks(String link) {
		Document doc = Html_to_Text(link);
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
	public static Document Html_to_Text(String link) {
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
		}
	}
	
	/**
	 * This function crawls the link provided by the user.
	 * @param link To be crawled
	 */
	public static void crawl_primary_link(String link) {
		write_to_crawled_page(link);
		System.out.println(link);
		String l2_links = crawl_sublinks(link);
		l2_crawling(l2_links);
	}
	
	
	/**
	 * This functions performs the level 2 crawling
	 * @param l2_links contains all the level 2 links
	 */
	public static void l2_crawling(String l2_links) {
		String l3_links = "";
		String [] array_l2_links = l2_links.split("\n");
		for (String l2_link : array_l2_links) {
			if(!l2_link.isEmpty())
			{
				write_to_crawled_page(l2_link);
				System.out.println(l2_link);
				l3_links = l3_links + crawl_sublinks(l2_link);
			}
		}
		if(!l3_links.isEmpty())l3_crawling(l3_links);
	}
	
	/**
	 * This functions performs the level 3 crawling
	 * @param l3_links contains all the level 3 links
	 */
	public static void l3_crawling(String l3_links) {
		String l4_links = "";
		String [] array_l3_links = l3_links.split("\n");
		for (String l3_link : array_l3_links) {
			if(!l3_link.isEmpty())
			{
				write_to_crawled_page(l3_link);
				System.out.println(l3_link);
				l4_links = l4_links +  crawl_sublinks(l3_link);
			}
		}
		if(!l4_links.isEmpty())l4_crawling(l4_links);
	}
	
	/**
	 * This functions performs the level 4 crawling
	 * @param l4_links contains all the level 4 links
	 */
	public static void l4_crawling(String l4_links) {
		String [] array_l4_links = l4_links.split("\n");
		for (String l4_link : array_l4_links) {
			if(!l4_link.isEmpty())
			{
				write_to_crawled_page(l4_link);
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
	public static void write_to_crawled_page(String link) {
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
		crawl_primary_link(string_link);
	}

}
