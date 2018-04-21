package com.lac.strategy;

public class Tourist {

	private TravelStrategy ts;

    public Tourist(TravelStrategy ts)
    {
        this.ts = ts;
    }

    public void executeStrategy()
    {
        ts.travel();
    }
}
