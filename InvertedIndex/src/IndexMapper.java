import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class IndexMapper extends Mapper<Object, Text, Text, Text> {
	
	private String [] stopwords = {"is", "that", "and", "or", "when", "where", "a"};
	private Text fileName;
	private Text word = new Text();
	  
	  @Override
	  protected void setup(Context context)
	      throws IOException, InterruptedException {
	    String f = ((FileSplit) context.getInputSplit()).getPath().getName();
	    System.out.println("@Setup:F= " + f);
	    fileName = new Text(f);
	  }
	  
	  @Override
	  protected void map(Object key, Text value, Context context)
	      throws IOException, InterruptedException {
	    
	    for(String token:  value.toString().split("\\s+")){
	      token = token.replaceAll( "[^\\p{L}\\p{N}]", "");
	      token = token.trim();
	      if(isNotStopWord(token)){
	      word.set(token);
	      context.write(word, fileName);
	      }
	    }
	  }
	  
	  private boolean isNotStopWord(String word){
		  boolean returnFlag = true;
		  for(int i=0; i< stopwords.length; i++){
			  if(word.toLowerCase().equals(stopwords[i].toLowerCase())){
				  returnFlag = false;
				  break;
			  }
			  
		  }
		  //
		  return returnFlag;
	  }

}
