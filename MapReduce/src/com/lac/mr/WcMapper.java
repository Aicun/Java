package com.lac.mr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	Set<String> skipPattern = new HashSet<String>();
	boolean caseSensitive = false;

	// ÿ�δ���map�����ᴫ��split��һ�����ݣ�key�������������ļ��е��±꣬valueΪ�������ݡ�
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = (caseSensitive) ? value.toString() : value.toString().toLowerCase(); 
		
		for(String pattern : skipPattern) {
			line = line.replaceAll(pattern, "");
		}
		
		StringTokenizer st = new StringTokenizer(value.toString());
		while (st.hasMoreTokens()) {
			String word = st.nextToken();
			context.write(new Text(word), new IntWritable(1));
		}
	}
	
	private void Configure() {
		caseSensitive = true;
	}

	private void parseSkipFile() {
		String pattern;
		try {
			FileReader fr = new FileReader("pattern.txt");
			BufferedReader br = new BufferedReader(fr);
			while ((pattern = br.readLine()) != null) {
				skipPattern.add(pattern);
			}
		} catch (IOException ioe) {
			System.err
					.println("Caught exception while parsing the cached file '"
							+ StringUtils.stringifyException(ioe));
		}
	}
}
