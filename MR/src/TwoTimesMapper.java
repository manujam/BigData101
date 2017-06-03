import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class TwoTimesMapper extends Mapper<LongWritable, Text, IntWritable, LongWritable> {
	
	private int mapperCount = 1;
	
	
	@Override
	protected void map(LongWritable inputKey, Text inputValue, Context context ) throws IOException, InterruptedException{
		long opNum = 0;
		if(inputValue.toString().length() > 0){
			 opNum = Long.parseLong(inputValue.toString());
		}
		
		if(mapperCount>4){
			mapperCount =1;
		}
		IntWritable key = new IntWritable(mapperCount); 
		mapperCount = mapperCount+1;
		context.write(key, new LongWritable(opNum*2));
		//super.map(inputKey, inputValue, context);
		
	}
	
}
