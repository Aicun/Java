package com.lac.hadoop.temperature;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
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

public class JobRun {
	
	public static DateFormat df = new SimpleDateFormat("yyyy-MM-DD");

	static class RecordMapper extends Mapper<LongWritable, Text, KeyPair, Text>{
		@Override
		protected void map(LongWritable key, Text value,Context context)
				throws IOException, InterruptedException {
			String record = value.toString();
			String[] detail = record.split("\t");
			if(detail.length==3){
				try {
					Date date = df.parse(detail[0]);
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					int year = c.get(Calendar.YEAR);
					String temperature = detail[2];
					KeyPair keyPair = new KeyPair();
					keyPair.setYear(year);
					keyPair.setDegree(Double.valueOf(temperature).doubleValue());
					context.write(keyPair, value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class RecordReducer extends Reducer<KeyPair, Text, KeyPair, Text>{
		@Override
		protected void reduce(KeyPair pair, Iterable<Text> iter,Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			for(Text text : iter){
				context.write(pair,text);
			}
		}
	}
	
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		try {
			Job job = new Job(conf);
			job.setJobName("temperature");
			job.setJarByClass(JobRun.class);
			job.setMapperClass(RecordMapper.class);
			job.setReducerClass(RecordReducer.class);
			job.setOutputKeyClass(KeyPair.class);
			job.setOutputValueClass(Text.class);
			
			job.setNumReduceTasks(8);
			job.setPartitionerClass(RecordPartition.class);
			job.setSortComparatorClass(SortKeyPair.class);
			job.setGroupingComparatorClass(KeyPairGroup.class);
			
			FileInputFormat.addInputPath(job, new Path("/root/hadoopdir/mapred/system/input/"));
			FileOutputFormat.setOutputPath(job, new Path("/root/hadoopdir/mapred/system/output/"));
			System.exit(job.waitForCompletion(true)?0:1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
