package com.lac.prototype;

import java.util.ArrayList;

public class ConcretePrototype extends Prototype {

	private ArrayList<String> list = new ArrayList<String>();
	
	public void show() {
		System.out.println("prototype implementation");
	}

	@Override
	public ConcretePrototype clone() {
		ConcretePrototype cp = null;
		
		cp = (ConcretePrototype) super.clone();
		cp.list = (ArrayList<String>) list.clone();
		return cp;
	}	
}
