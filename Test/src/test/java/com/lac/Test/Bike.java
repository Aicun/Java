package com.lac.Test;

public class Bike {
	private int height;
	private int radius;
	private float speed;
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public Bike(int h,int s){
		this.height = h;
		this.speed = s;
	}
	
	public Bike(){
		
	}
}
