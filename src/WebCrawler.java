import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	 * @param args
	 */
	public static String crawled_links = "";
	public static String crawl_sublinks(String link) {
		try {
			Document doc = Jsoup.connect(link).get();
			Elements es = doc.select("a");
			String links = "";
			for(Element e : es) {
				String href = e.attr("abs:href");
				if(href.length()>3)
				{
					links = links+"\n"+href;
				}
			}
			return links;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	public static void crawl_link(String link) {
		
			crawled_links += link+"\n";
			String list_of_sublinks = crawl_sublinks(link);
			String [] array_list_of_sublinks = list_of_sublinks.split("\n");
			for (String string_al : array_list_of_sublinks) {
				
				if(!string_al.isEmpty()) {
					
					crawled_links += string_al+"\n";
					System.out.println(string_al);
				}
			}
		
		
	}
	public static void write_crawled_links() {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("crawledlinks.txt"));
			writer.write(crawled_links);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void Html_to_Text() {
		String [] array_list_of_crawled_links = crawled_links.split("\n");
		for (String crawled_link : array_list_of_crawled_links) {
			try {
				Document doc = Jsoup.connect(crawled_link).get();
				String title = doc.title().replace("|","_").replace("?", "_").replace("\\","_"); 
				BufferedWriter writer = new BufferedWriter(new FileWriter("Webpages/"+ title + ".txt"));
				writer.write(doc.text());
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string_link = "https://www.cbc.ca/";
		crawl_link(string_link);
		write_crawled_links();
		System.out.println("#######################\n\n");
		Html_to_Text();
	}

}
