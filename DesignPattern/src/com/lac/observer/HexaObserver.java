package com.lac.observer;

public class HexaObserver extends Observer {

	public HexaObserver(Subject subject){
		this.subject = subject;
		subject.addObserver(this);
	}
	
	@Override
	public void update() {
		System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ) ); 
	}
}
