package com.lac.businessDelegate;

import com.lac.observer.BusinessLookUp;

public class BusinessDelegate {

	private BusinessLookUp lookupService = new BusinessLookUp();
	private BusinessService bs;
	private String serviceType;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void doTask() {
		bs = lookupService.getBusinessService(serviceType);
		bs.doProcessing();
	}
}
