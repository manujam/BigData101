package wiki;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WikiReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	
	
	
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,Context context) 
			throws IOException, InterruptedException{
		
		long totalCount = 0;
		
		for (LongWritable value:values){
			totalCount+= Long.parseLong(value.toString());
		}
		
		context.write(key, new LongWritable(totalCount));
	}

}
