package com.lac.abstractFactory;

public interface AbstractFactory {
	public Color getColor(String color);
    public Shape getShape(String shape);
}
