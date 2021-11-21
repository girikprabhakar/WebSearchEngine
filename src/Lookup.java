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
		Result sample=new Result();
		sample.heading="hey"+r.nextFloat();
		sample.link="https://google.com";
		sample.description="hey ther!";
		results.add(sample);
		return results;
	}
}
