package com.lac.servicelocator;

import java.util.HashMap;
import java.util.Map;

public class Cache {

	public Map<String,Service> services;
	
	public Cache(){
		services = new HashMap<String,Service>();
	}
	
	public Service getService(String serviceName){
		Service service = services.get(serviceName);
		return service;
	}
	
	public void addService(String serviceName,Service service){
		Service s = services.get(serviceName);
		if(s==null){
			services.put(serviceName, service);
		}
	}
}
