package com.lac.strategy;

public class TestStrategy {

	public static void main(String[] args) {
		TravelStrategy strategy = new AirPlanelStrategy();
		Tourist tourist = new Tourist(strategy);
		tourist.executeStrategy();
		
		strategy = new TrainStrategy();
		Tourist tourist2 = new Tourist(strategy);
		tourist2.executeStrategy();
	}
}
