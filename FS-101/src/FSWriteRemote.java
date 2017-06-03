import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


public class FSWriteRemote {
	
	private final static String SOURCE_URL = "http://finance.yahoo.com/d/quotes.csv?f=snd1|1yr&s=ADBE";

	public static void main(String[] args) throws IOException {
			URL url = new URL(SOURCE_URL);
			InputStream in  = url.openStream();
			Configuration config = new Configuration();
			FileSystem fs = FileSystem.get(config);
			OutputStream out = fs.create(new Path(args[0]));
			IOUtils.copyBytes(in, out , 4096);
			
	}

}
