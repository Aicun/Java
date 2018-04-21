package com.lac.Test;

public class MountBike extends Bike{
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public static void main(String args[]){
		MountBike mb = new MountBike();
		System.out.println(mb.getRadius());
		for(Planet p :Planet.values()){
			p.compare();
		};
	}
	 
}
