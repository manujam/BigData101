package wiki;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class WikiMapper extends Mapper<Object, Text, Text, LongWritable> {
	
	protected void map(Object offset, Text wikiLine, Context context) throws IOException, InterruptedException{
		
		//String[] lineItems = wikiLine.toString().split(" ");
		
		StringTokenizer wikiTokens = new StringTokenizer(wikiLine.toString());
		
		wikiTokens.nextToken();
		
		// String currentPath = lineItems[1].toString(); 
		 String siteURL = wikiTokens.nextToken();
		// long siteCount = Long.parseLong(lineItems[2].toString()); 
		 wikiTokens.nextToken();
		long siteVisits = Long.parseLong(wikiTokens.nextToken());
		
		//context.write(new Text(currentPath), new LongWritable(siteCount));
		context.write(new Text(siteURL), new LongWritable(siteVisits));
		
		
	}
	
}
