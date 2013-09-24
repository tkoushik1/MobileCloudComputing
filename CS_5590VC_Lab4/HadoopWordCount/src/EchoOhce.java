import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*;
import java.net.*;
import java.util.regex.MatchResult;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class EchoOhce extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
	    int res = ToolRunner.run(new Configuration(), new EchoOhce(), args);
	    System.exit(res);
	  }

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JobConf conf = new JobConf(getConf(), EchoOhce.class);
	    conf.setJobName("EchoOhce");

	    // the keys are strings
	    conf.setOutputKeyClass(Text.class);
	    // the values are reverse strings
	    conf.setOutputValueClass(Text.class);

	    conf.setMapperClass(Map.class);
	    conf.setCombinerClass(Reduce.class);
	    conf.setReducerClass(Reduce.class);

	    List<String> other_args = new ArrayList<String>();
	    for(int i=0; i < args.length; ++i) {
	      try {
	        if ("-m".equals(args[i])) {
	          conf.setNumMapTasks(Integer.parseInt(args[++i]));
	        } else if ("-r".equals(args[i])) {
	          conf.setNumReduceTasks(Integer.parseInt(args[++i]));
	        } else {
	          other_args.add(args[i]);
	        }
	      } catch (NumberFormatException exception) {
	        System.out.println("Give int value instead of " + args[i]);
	        //return printUsage();
	      } catch (ArrayIndexOutOfBoundsException exception) {
	        System.out.println("Parameter missing " +  args[i-1]);
	        //return printUsage();
	      }
	    }
	  
	    if (other_args.size() != 2) {
	      
	      //return printUsage();
	    }
	    conf.setInputFormat(TextInputFormat.class);
	      conf.setOutputFormat(TextOutputFormat.class);
	      FileInputFormat.setInputPaths(conf, new Path(args[0]));
	      FileOutputFormat.setOutputPath(conf, new Path(args[1]));

	    JobClient.runJob(conf);
	    return 0;
	}
}
