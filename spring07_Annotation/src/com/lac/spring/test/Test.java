package com.lac.spring.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lac.service.impl.CustomerService2;

public class Test {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		//CustomerService2  cust = (CustomerService2 ) context.getBean("customerService2");
		//System.out.println(cust);
		context.close();
	}
}
