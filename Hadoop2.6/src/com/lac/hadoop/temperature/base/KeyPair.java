package com.lac.hadoop.temperature.base;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class KeyPair implements WritableComparable<KeyPair>{
	
	private int year;
	private double degree;
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	@Override
	public void readFields(DataInput di) throws IOException {
		// TODO Auto-generated method stub
		this.year = di.readInt();
		this.degree = di.readDouble();
	}

	@Override
	public void write(DataOutput dop) throws IOException {
		// TODO Auto-generated method stub
		dop.writeInt(year);
		dop.writeDouble(degree);
	}
	
	@Override
	public int compareTo(KeyPair o) {
		int res = new Integer(year).compareTo(new Integer(o.getYear()));
		return res == 0 ? new Double(degree).compareTo(new Double(o.getDegree())):res;
	}

	public String toString(){
		return year+"\t"+degree;
	}
	
	public int hashCode(){
		return new Double(year+degree).hashCode();
	}
}
