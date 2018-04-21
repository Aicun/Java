package com.lac.hadoop.advertise;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text text, Iterable<IntWritable> iter,Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable i : iter) {
			sum += i.get();
		}
		if(text.equals(new Text("count"))){
			System.out.println(text.toString()+"#############"+ sum);
		}
		context.write(text, new IntWritable(sum));
	}
}
