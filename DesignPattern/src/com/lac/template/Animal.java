package com.lac.template;

public abstract class Animal {
	public abstract void Eat();
    public abstract void Run();

    public void animialBehavior()
    {
        Eat();
        Run();
        System.out.printf(" animial %s eat and run",this.getClass().getName());
    }
}
