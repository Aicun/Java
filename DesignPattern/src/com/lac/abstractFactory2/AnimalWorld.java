package com.lac.abstractFactory2;

public class AnimalWorld {

	private Herbivore _herbivore;
	private Carnivore _carnivore;

	// Constructor
	public AnimalWorld(ContinentFactory factory) {
		_carnivore = factory.CreateCarnivore();
		_herbivore = factory.CreateHerbivore();
	}

	public void run() {
		_carnivore.eat(_herbivore);
	}

	public static void main(String args[]) {
		ContinentFactory africa = new AfricaFactory();
		AnimalWorld aw = new AnimalWorld(africa);
		aw.run();

		ContinentFactory america = new AmericaFactory();
		aw = new AnimalWorld(america);
		aw.run();

	}
}
