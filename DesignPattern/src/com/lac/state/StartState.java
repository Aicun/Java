package com.lac.state;

public class StartState implements State {

	@Override
	public void action(Context context) {
		System.out.println("Player is in start state");
		context.setState(this);
	}

	public String toString() {
		return "Start State";
	}
}
