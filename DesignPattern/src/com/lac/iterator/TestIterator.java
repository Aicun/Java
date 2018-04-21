package com.lac.iterator;

public class TestIterator {

	public static void main(String[] args) {
		NameContainer nc = new NameContainer();
		for(Iterator iter = nc.getIterator();iter.hasNext();)
		{
			String name = (String)iter.next();
	        System.out.println("Name : " + name);
		}
	}
}
