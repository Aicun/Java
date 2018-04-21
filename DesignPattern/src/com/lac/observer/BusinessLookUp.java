package com.lac.observer;

import com.lac.businessDelegate.BusinessService;
import com.lac.businessDelegate.EJBService;
import com.lac.businessDelegate.JMSService;

public class BusinessLookUp {

	public BusinessService getBusinessService(String serviceType) {
		if (serviceType.equalsIgnoreCase("EJB")) {
			return new EJBService();
		} else {
			return new JMSService();
		}
	}
	
}
