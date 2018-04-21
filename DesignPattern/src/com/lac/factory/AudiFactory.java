package com.lac.factory;

public class AudiFactory implements CarFactory {

	@Override
	public Car makeCar() {
		return new Audi();
	}

}
