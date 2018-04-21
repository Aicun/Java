package com.lac.abstractFactory;

public class ShapeFactory implements AbstractFactory{

	@Override
	public Color getColor(String color) {
		return null;
	}

	@Override
	public Shape getShape(String shapeType) {
		Shape s = null;

        if ("Circle".equalsIgnoreCase(shapeType))
        {
            s = new Circle();

        }
        else if ("Rectangle".equalsIgnoreCase(shapeType))
        {
            s = new Rectangle();

        }
        else if ("Square".equalsIgnoreCase(shapeType))
        {
            s = new Square();
        }

        return s;
	}

}
