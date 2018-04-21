package com.lac.hadoop.advertise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;

public class LastMapper extends Mapper<LongWritable, Text, Text, Text> {

	private static Map<String,Integer> cmap = null;//count
	private static Map<String,Integer> df = null;//df
	
	//在map前要拿到两个文件的内容
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		if(cmap == null || cmap.size() == 0 || df == null || df.size() == 0){
			URI[] uris = context.getCacheFiles();
			if(uris!=null) {
				for(URI uri : uris) {
					if(uri.getPath().endsWith("part-r-00003")) {
						BufferedReader br = new BufferedReader(new FileReader(new File(uri)));
						String line = br.readLine();
						if(line.startsWith("count")) {
							String[] ls = line.split("\t");
							cmap = new HashMap<String,Integer>();
							cmap.put(ls[0],Integer.parseInt(ls[1].trim()));
						}
						br.close();	
					}else if(uri.getPath().endsWith("part-r-00000")) {
						df = new HashMap<String,Integer>();
						BufferedReader br = new BufferedReader(new FileReader(new File(uri)));
						String line;
						while((line = br.readLine())!=null) {
							String[] ss = line.split("\t");
							df.put(ss[0],Integer.parseInt(ss[1].trim()));
						}
						br.close();
					}
				}
			}
		}
	}
	
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		FileSplit fs = (FileSplit) context.getInputSplit();
		if(!fs.getPath().getName().contains("part-r-00003")) {
			String[] v = value.toString().trim().split("\t");
			if(v.length>=2) {
				int tf = Integer.parseInt(v[1].trim());
				String[] ss = v[0].split("_");
				if(ss.length>=2) {
					String w = ss[0];
					String id = ss[1];
					
					double s = tf * Math.log(cmap.get("count")/df.get(w));
					NumberFormat nf =NumberFormat.getInstance();
					nf.setMaximumFractionDigits(5);
					context.write(new Text(id), new Text(w+":"+nf.format(s)));
				}
			}
			else {
				System.out.println(value.toString()+"#############");
			}
		}
	}
}
