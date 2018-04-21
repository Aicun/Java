package com.lac.abstractFactory2;

public class Lion implements Carnivore{

	@Override
	public void eat(Herbivore h) {
		System.out.println(this.getClass().getTypeName() + " eats " + h.getClass().getTypeName());
	}

}
