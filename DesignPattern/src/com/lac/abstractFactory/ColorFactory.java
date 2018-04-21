package com.lac.abstractFactory;

public class ColorFactory implements AbstractFactory{

	@Override
	public Color getColor(String color) {
		Color c = null;
        if ("Red".equalsIgnoreCase(color))
        {
            c= new Red();
        }
        else if ("Green".equalsIgnoreCase(color))
        {
            c= new Green();
        }
        else if ("Blue".equalsIgnoreCase(color))
        {
            c= new Blue();
        }
        return c;
	}

	@Override
	public Shape getShape(String shape) {
		// TODO Auto-generated method stub
		return null;
	}

}
