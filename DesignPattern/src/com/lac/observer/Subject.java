package com.lac.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

	private List<Observer> observerList = new ArrayList<Observer>();
	private int state;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
		notifyAllObservers();
	}
	
	public void addObserver(Observer observer){
		observerList.add(observer);
	}
	
	public void notifyAllObservers(){
		for(Observer observer :observerList){
			observer.update();
		}
	}
}
