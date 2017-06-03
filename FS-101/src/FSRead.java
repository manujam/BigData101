import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


public class FSRead {

	public static void main(String[] args) throws IOException {
		
		
		// contains all the config from core-site, hdfs-site, mapreduce-site xml files
		
		Configuration config = new Configuration();
		
		// Get the reference of the Hadoop File System
		FileSystem fs = FileSystem.get(config);
		
		
		// Open the file and get its reference in InputStream
		InputStream in  = fs.open(new Path(args[0])); // load the hello.txt passed in args
		
		// Print data in chunks of 4KB
		IOUtils.copyBytes(in, System.out , 4096);
		
	}

}
