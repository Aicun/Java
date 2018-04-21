package com.lac.observer;

public class BinaryObserver extends Observer {

	public BinaryObserver(Subject subject){
		this.subject = subject;
		subject.addObserver(this);
	}
	
	@Override
	public void update() {
		System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) ); 
	}
}
