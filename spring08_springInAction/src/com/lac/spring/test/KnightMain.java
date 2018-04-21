package com.lac.spring.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lac.spring.beans.Knight;

public class KnightMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Knight knight = context.getBean(Knight.class);
		knight.embarkOnQuest();
		context.close();
	}

}
