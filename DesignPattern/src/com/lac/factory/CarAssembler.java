package com.lac.factory;

public class CarAssembler {

	private static CarFactory cf;
	public static Car AssembleCar(String carMaker){
		Car car;
		if(carMaker.equalsIgnoreCase("Benz")){
			cf = new BenzFactory();
			car =cf.makeCar();
		}else{
			cf = new AudiFactory();
			car =cf.makeCar();
		}
		return car;
	}
}
