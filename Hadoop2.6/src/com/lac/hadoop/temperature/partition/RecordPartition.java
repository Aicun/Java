package com.lac.hadoop.temperature.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import com.lac.hadoop.temperature.base.KeyPair;

public class RecordPartition extends Partitioner<KeyPair, Text>{

	@Override
	public int getPartition(KeyPair kp, Text text, int num) {
		int year = kp.getYear() % num;
		return year;
	}

}
