package com.lac.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IMyService {

	public int add(@WebParam(name="a")int a,@WebParam(name="a")int b);
	public int minus(@WebParam(name="a")int a,@WebParam(name="a")int b);
	public String hello(@WebParam(name="firstName")String firstName,@WebParam(name="lastName")String lastName);
}
