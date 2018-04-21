package com.lac.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MrRun {
	
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "node1:9001");
		conf.set("fs.default.name", "hdfs://node1:9000");
		conf.set("mapred.jar", "C:\\Users\\flyapple88\\Desktop\\mr.jar");
		try {
			Job job = new Job(conf);
			job.setJarByClass(MrRun.class);
			job.setMapperClass(WcMapper.class);
			job.setReducerClass(WcReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			job.setNumReduceTasks(1);//设置reduce任务个数
			
			FileInputFormat.addInputPath(job, new Path("/root/hadoopdir/mapred/system/input/"));
			FileOutputFormat.setOutputPath(job, new Path("/root/hadoopdir/mapred/system/output/"));
			System.exit(job.waitForCompletion(true)?0:1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
