package com.lac.hadoop.temperature.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.lac.hadoop.temperature.base.KeyPair;


public class KeyPairGroup extends WritableComparator{

	public KeyPairGroup() {
		super(KeyPair.class,true);
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		KeyPair kp1 = (KeyPair) a;
		KeyPair kp2 = (KeyPair) b;
		return new Integer(kp1.getYear()).compareTo(new Integer(kp2.getYear()));
	}
	
	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2,
			int l2) {
		int i1 = readInt(b1, s1);
        int i2 = readInt(b2, s2);
         
        return (i1 < i2) ? -1 : (i1 == i2) ? 0 : 1;
	}
}
