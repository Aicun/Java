package com.lac.servicelocator;

public class Service2 implements Service {

	@Override
	public String getName() {
		  return this.getClass().getName();
	}

	@Override
	public void execute() {
		  System.out.println("Executing Service2");
	}

}
