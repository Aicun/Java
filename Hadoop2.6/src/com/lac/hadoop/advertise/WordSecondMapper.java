package com.lac.hadoop.advertise;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;


/***
 * 
 * @author flyapple88
 *
 *TF---fileA
 *worda_0001	1
 *wordb_0001	2
 *
 *N----fileB
 *count	11111111
 */
public class WordSecondMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		FileSplit fs = (FileSplit) context.getInputSplit();
		if(!fs.getPath().getName().contains("part-s-00003")) {//统计非微博条数
			String v[] = value.toString().trim().split("\t");
			if(v.length >= 2) {
				String[] ss = v[0].split("_");
				if(ss.length>=2) {
					String w = ss[0];
					//统计DF,关键字在所有微博中出现的总数（一条微博中多次算一次）
					context.write(new Text(w), new IntWritable(1));
				}
			}
		} else {
			System.out.println(value.toString() + "###########");
		}
			
	}
}
