import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Lookup {
	public class Result{
		String heading;
		String link;
		String description;
		int rank;
	}

	public ArrayList<Lookup.Result> searchTerm(String s) {
		Random r=new Random();
		ArrayList<Result> results=new ArrayList<Result>();

		Search.searchPhrase(s,15);
		for(HashMap<String,String> element:Search.searchPhrase(s,15)) {
			Result sample=new Result();
			sample.heading=element.getOrDefault("title", "");
			sample.link=element.getOrDefault("link", "");
			sample.description=element.getOrDefault("description", "");
			results.add(sample);
		}
		
		return results;
	}
}
