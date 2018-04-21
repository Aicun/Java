package com.lac.ws;

import javax.jws.WebService;

@WebService(endpointInterface="com.lac.ws.IMyService")
public class MyServiceImpl implements IMyService {

	@Override
	public int add(int a, int b) {
		System.out.println(a+b);
		return a+b;
	}

	@Override
	public int minus(int a, int b) {
		System.out.println(a-b);
		return a-b;
	}

	@Override
	public String hello(String firstName, String lastName) {
		String sayHello = "Hello " + firstName +" "+ lastName;
		System.out.println(sayHello);
		return sayHello;
	}

}
