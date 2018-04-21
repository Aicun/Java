package com.lac.businessDelegate;

public class TestDelegate {

	public static void main(String[] args) {
		BusinessDelegate bd = new BusinessDelegate();
		bd.setServiceType("EJB");
		Client client = new Client(bd);
		client.doTask();

		bd.setServiceType("JMS");
		client.doTask();
	}
}
