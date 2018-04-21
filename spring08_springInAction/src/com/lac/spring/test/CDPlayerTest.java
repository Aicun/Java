package com.lac.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lac.spring.aop.CompactDisc;
import com.lac.spring.autowire.CDPlayerConfig;
import com.lac.spring.beans.Knight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {

	//@Autowired
	private CompactDisc cd;
	
	@Autowired
	private Knight knight;

	@Test
	public void cdShouldNotBeNull() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CompactDisc cccc = (CompactDisc) context.getBean("sgtPeppers");
		cccc.play(1);
		context.close();
		//assertNotNull(cd);
	}
	
	@Test
	public void knightTest(){
		knight.embarkOnQuest();
	}
	
	
}
