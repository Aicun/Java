package com.lac.service.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class CustomerService2 implements InitializingBean,DisposableBean {
	String message;

	public String getMessage() {
	  return message;
	}

	public void setMessage(String message) {
	  this.message = message;
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Spring Container is destroy! Customer clean up");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Init method after properties are set : " + message);
	}

}
