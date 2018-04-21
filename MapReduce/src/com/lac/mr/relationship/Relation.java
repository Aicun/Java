package com.lac.mr.relationship;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Relation extends Mapper<LongWritable, Text, Text, Text>{

	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		String relations[] = line.split("\t");
		
		context.write(new Text(relations[0]), new Text(relations[1]));
		context.write(new Text(relations[1]), new Text(relations[0]));
	}
}
