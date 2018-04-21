package com.lac.factory;

public class TestFactory {

	public static void main(String[] args) {
		Car car = CarAssembler.AssembleCar("benz");
		System.out.println(car.getClass().getName()+" speed is "+ car.speed());
	}

}
