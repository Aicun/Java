package com.lac.factory;

public class BenzFactory implements CarFactory {

	@Override
	public Car makeCar() {
		return new Benz();
	}

}
