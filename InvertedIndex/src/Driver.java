import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Driver {

  @SuppressWarnings("deprecation")
public static void main(String[] args)
      throws IOException, InterruptedException, ClassNotFoundException {

    Configuration conf = new Configuration();
    Job job = new Job(conf);

    job.setMapperClass(IndexMapper.class);
    job.setReducerClass(IndexReducer.class);

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);

    job.setJarByClass(Driver.class);

    job.waitForCompletion(true);

  }

}
