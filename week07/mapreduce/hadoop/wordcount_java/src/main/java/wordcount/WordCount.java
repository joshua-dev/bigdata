package wordcount;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

public class WordCount extends Configured implements Tool {

  public int run(String[] strings) throws Exception {
    Job job = Job.getInstance(getConf());
    job.setJarByClass(WordCount.class);

    job.setMapperClass(WordCountMapper.class);
    job.setReducerClass(WordCountReducer.class);
    job.setCombinerClass(WordCountReducer.class);

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);

//    job.setInputFormatClass(TextInputFormat.class);
//    job.setOutputFormatClass(TextOutputFormat.class);

    FileInputFormat.addInputPath(job, new Path(strings[0]));
    FileOutputFormat.setOutputPath(job, new Path(strings[0] + ".out"));

    job.waitForCompletion(true);

    return 0;
  } // end method run.

} // end class WordCount.