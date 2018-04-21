package com.lac.strategy;

public class TrainStrategy implements TravelStrategy {

	@Override
	public void travel() {
		System.out.println("travel by trains");
	}

}
