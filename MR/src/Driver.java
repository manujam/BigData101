import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Configuration config = new Configuration();
		
		Job job = new Job(config);
		
		job.setMapperClass(TwoTimesMapper.class);
		job.setReducerClass(SumReducer.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(LongWritable.class);
		
		job.setCombinerClass(SumReducer.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0])); // first argument is input file
		FileOutputFormat.setOutputPath(job, new Path(args[1])); // where to write final output
		
		job.setJarByClass(Driver.class);
		
		job.waitForCompletion(true);
		

	}

}
