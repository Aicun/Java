package com.lac.hadoop.advertise;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class WordPartition extends HashPartitioner<Text, IntWritable> {

	/**
	 * if key equals count,participate to 3
	 * others participate to other 
	 */
	@Override
	public int getPartition(Text key, IntWritable value, int numReduceTasks) {
		if(key.equals(new Text("count"))) {
			return 3;
		}
		else {
			return super.getPartition(key, value, numReduceTasks-1);
		}
	}
}
