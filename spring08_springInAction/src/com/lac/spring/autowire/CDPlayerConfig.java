package com.lac.spring.autowire;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.lac.spring.beans.BraveKnight;
import com.lac.spring.beans.Knight;
import com.lac.spring.beans.Quest;
import com.lac.spring.beans.SlayDragonQuest;

@Configuration
@ComponentScan
public class CDPlayerConfig {

	@Bean
	@Conditional(MagicExistsCondition.class)
	public Quest quest(){
		return new SlayDragonQuest(System.out);
	}
	
	@Bean
	public Knight knight(){
		return new BraveKnight(quest(),"super hero");
	}
	
	@Bean
	static PropertySourcesPlaceholderConfigurer placeHolderConfigure(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}
