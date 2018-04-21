package com.lac.state;

public class StopState implements State {

	@Override
	public void action(Context context) {
		System.out.println("Player is in stop state");
		context.setState(this);
	}

	public String toString() {
		return "Stop State";
	}
}
