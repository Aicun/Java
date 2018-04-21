package com.lac.spring.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lac.spring.exception.SpittleNotFoundException;

@ControllerAdvice
public class AppWideExceptionHandler {

	@ExceptionHandler(SpittleNotFoundException.class)
	public String duplicateSpittleHandler(){
		return "error/duplicate";
	}
}
