import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class IndexReducer extends
    Reducer<Text, Text, Text, Text> {
  
  private Text fileNames = new Text();
  
  @Override
  protected void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {

    HashSet<String> fileNameSet = new HashSet<String>();
    
    for(Text fileName: values)
      fileNameSet.add(fileName.toString());
    
    fileNames.set(fileNameSet.toString());
    context.write(key, fileNames);
  }

}
