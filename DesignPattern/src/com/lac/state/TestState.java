package com.lac.state;

public class TestState {

	public static void main(String[] args) {
		Context context = new Context();

		StartState startState = new StartState();
		startState.action(context);
		
		StopState stopState = new StopState();
		stopState.action(context);
	}

}
