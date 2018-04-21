package com.lac.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

public class MyServletInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		//Dynamic myServlet = servletContext.addServlet("myServlet", (Class<? extends Servlet>) MyServlet.class);
		//myServlet.addMapping("/custom/**");
		
/*		DispatcherServlet ds = new DispatcherServlet();
		Dynamic registration = context.addServlet("appServlet", ds);
		registration.addMapping("/");
		registration.setMultipartConfig(
		new MultipartConfigElement("/tmp/spittr/uploads"));*/
	}

}
