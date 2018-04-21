package com.lac.hadoop.advertise;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LastReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text text, Iterable<Text> iter,Context context)
			throws IOException, InterruptedException {
		StringBuffer sb = new StringBuffer();
		for(Text t:iter) {
			sb.append(t).append("\t");
		}
		context.write(text, new Text(sb.toString()));
	}
}
