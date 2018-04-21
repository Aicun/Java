package com.lac.abstractFactory2;

public class AmericaFactory implements ContinentFactory{

	@Override
	public Herbivore CreateHerbivore() {
		return new Bison();
	}

	@Override
	public Carnivore CreateCarnivore() {
		return new Wolf();
	}

}
