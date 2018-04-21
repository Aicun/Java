package com.lac.spring.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//for setting up DispatcherServlet and ContextLoaderListener, used after servlet3.0
public class SpittrWebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		super.customizeRegistration(registration);
		registration.setMultipartConfig(new MultipartConfigElement("D:/tmp"));
		registration.setLoadOnStartup(1);
	}
	
	
	//filter returned from getServletFilters() 
	//will automatically be mapped to DispatcherServlet.
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		return super.getServletFilters();
	}
}
