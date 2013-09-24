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
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
public class Map extends MapReduceBase
        implements Mapper<LongWritable, Text, Text, Text> {

        private Text inputText   = new Text();
        private Text reverseText = new Text();

        public void map(LongWritable key, Text inputs,
                        OutputCollector<Text, Text> output,
                        Reporter reporter) throws IOException {

                String inputString = inputs.toString();
                int length = inputString.length();
                StringBuffer reverse = new StringBuffer();
                for(int i=length-1; i>=0; i--)
                {
                    reverse.append(inputString.charAt(i));
                }
                inputText.set(inputString);
                reverseText.set(reverse.toString());
                output.collect(inputText,reverseText);
        }
    }