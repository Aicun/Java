package com.lac.spring.config;

import java.io.IOException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
// Enable Spring MVC
@ComponentScan("com.lac.spring.controller")
@PropertySource("classpath:ValidationMessages.properties")
// Enable component-scanning, base package
public class WebConfig extends WebMvcConfigurerAdapter {

	// Configure a JSP view resolver
	// @Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		// a view name of home will be resolved as /WEB-INF/views/home.jsp
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	/*
	 * @Bean public static PropertySourcesPlaceholderConfigurer
	 * placeholderConfigurer() { return new
	 * PropertySourcesPlaceholderConfigurer(); }
	 */

	// configure message source
	/*
	 * @Bean public MessageSource messageSource() { ResourceBundleMessageSource
	 * messageSource = new ResourceBundleMessageSource(); // set to any value
	 * you like messageSource.setBasename("messages_zh"); return messageSource;
	 * }
	 */
	// another message source that can reload message properties without
	// recompiling or restarting the application
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// messageSource.setBasename("file:///etc/spittr/messages");
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "/WEB-INF/layout/tiles.xml",
				"/WEB-INF/views/**/tiles.xml" });
		return tiles;
	}

	@Bean
	// Tile view
	public ViewResolver tilesViewResolver() {
		return new TilesViewResolver();
	}

	//@Bean
	public ViewResolver thymeleafViewResolver(
			SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver();
	}

	//@Bean
	public TemplateEngine templateEngine(ServletContextTemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}

	//@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(null);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}
	
	//for users to upload files,StandardServletMultipartResolver is used for servlet 3.0 and spring 3.1 or higher
	//otherwise use CommonsMultipartResolver(Jakarta Commons FileUpload)
	@Bean
	public MultipartResolver multipartResolver() throws IOException{
		StandardServletMultipartResolver ssmr = new StandardServletMultipartResolver();
		return ssmr;
		
	/*	CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setUploadTempDir(new FileSystemResource("/tmp/spittr/uploads"));
		cmr.setMaxUploadSize(2097152);
		cmr.setMaxInMemorySize(0);
		return cmr;*/
	}

	// Configure static content handling
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}