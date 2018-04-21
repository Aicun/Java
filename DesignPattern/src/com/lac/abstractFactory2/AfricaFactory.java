package com.lac.abstractFactory2;

public class AfricaFactory implements ContinentFactory {

	@Override
	public Herbivore CreateHerbivore() {
		return new Wildebeest();
	}

	@Override
	public Carnivore CreateCarnivore() {
		return new Lion();
	}

}
