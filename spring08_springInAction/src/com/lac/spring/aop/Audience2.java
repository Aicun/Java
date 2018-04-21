package com.lac.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class Audience2 {

	public void silenceCellPhones() {
		System.out.println("Audience2 Silencing cell phones");
	}

	public void takeSeats() {
		System.out.println("Audience2 Taking seats");
	}

	public void applause() {
		System.out.println("Audience2 CLAP CLAP CLAP!!!");
	}

	public void demandRefund() {
		System.out.println("Audience2 Demanding a refund");
	}

	public void watchPerformance(ProceedingJoinPoint jp) {
		try {
			System.out.println("Audience2 Silencing cell phones");
			System.out.println("Audience2 Taking seats");
			jp.proceed();
			System.out.println("Audience2 CLAP CLAP CLAP!!!");
		} catch (Throwable e) {
			System.out.println("Audience2 Demanding a refund");
		}
	}
}