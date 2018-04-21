package com.lac.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {

	@Pointcut("execution(** concert.Performance.perform(..))")
	public void performance() {
	}

	// @Before("execution(** com.lac.spring.aop.Performance.perform(..))")
	@Before("performance()")
	public void silenceCellPhones() {
		System.out.println("Silencing cell phones");
	}

	@Before("execution(** com.lac.spring.aop.Performance.perform(..))")
	public void takeSeats() {
		System.out.println("Taking seats");
	}

	@AfterReturning("execution(** com.lac.spring.aop.Performance.perform(..))")
	public void applause() {
		System.out.println("CLAP CLAP CLAP!!!");
	}

	@AfterThrowing("execution(** com.lac.spring.aop.Performance.perform(..))")
	public void demandRefund() {
		System.out.println("Demanding a refund");
	}

	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint jp) {
		try {
			System.out.println("Silencing cell phones");
			System.out.println("Taking seats");
			jp.proceed();
			System.out.println("CLAP CLAP CLAP!!!");
		} catch (Throwable e) {
			System.out.println("Demanding a refund");
		}
	}
}