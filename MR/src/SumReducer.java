import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class SumReducer extends Reducer<IntWritable, LongWritable, IntWritable, LongWritable> {

	@Override
	protected void reduce(IntWritable key, Iterable<LongWritable> values,Context context) throws IOException, InterruptedException{
		
		long sum = 0;
		
		for(LongWritable value:values){
			sum+= value.get();
		}
		
		context.write(key, new LongWritable(sum));
		
	}
}
