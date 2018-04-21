package com.lac.mr.relationship;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RelationReducer extends Reducer<Text, Text, Text, Text>{

	@Override
	protected void reduce(Text text, Iterable<Text> iter,
			Context context)
			throws IOException, InterruptedException {
		
		Set<String> set = new HashSet<String>();
		for(Text t:iter ) {
			set.add(t.toString());
		}
		if(set.size()>1) {
			for(Iterator<String> i=set.iterator();i.hasNext();) {
				String name = i.next();
				for(Iterator<String> k=set.iterator();k.hasNext();) {
					String other = k.next();
					if(!name.equals(other)){
						context.write(new Text(name), new Text(other));
					}
				}
			}
		}
	}
}
