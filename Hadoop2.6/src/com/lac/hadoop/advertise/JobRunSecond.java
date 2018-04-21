package com.lac.hadoop.advertise;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import com.lac.hadoop.temperature.base.KeyPair;
import com.lac.hadoop.temperature.order.SortKeyPair;
import com.lac.hadoop.temperature.partition.RecordPartition;
import com.lac.hadoop.temperature.sort.KeyPairGroup;

public class JobRunSecond {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		try {
			Job job = new Job(conf);
			job.setJarByClass(JobRunSecond.class);
			job.setJobName("advertise2");
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			//job.setNumReduceTasks(4);
			
			//job.setPartitionerClass(WordPartition.class);
			job.setMapperClass(WordSecondMapper.class);
			job.setCombinerClass(WordSecondReducer.class);
			job.setReducerClass(WordSecondReducer.class);
		
			job.setPartitionerClass(RecordPartition.class);
			
			FileInputFormat.addInputPath(job, new Path("/root/hadoopdir/mapred/system/output/"));
			FileOutputFormat.setOutputPath(job, new Path("/root/hadoopdir/mapred/system/output2/"));

			boolean f = job.waitForCompletion(true);
			if(f) {
				System.out.println("finished job");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
