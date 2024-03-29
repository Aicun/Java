package com.lac.spring.aop;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrackCounterConfig.class)
public class TrackCounterTest {

	@Autowired
	private CompactDisc cd;
	
	@Autowired
	private TrackCounter counter;

	@Test
	public void testTrackCounter() {
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		//CompactDisc cd = context.getBean(SgtPeppers.class);
		//TrackCounter counter = context.getBean(TrackCounter.class);
		cd.play(1);
		cd.play(2);
		cd.play(3);
		cd.play(3);
		cd.play(3);
		cd.play(3);
		cd.play(7);
		cd.play(7);
		assertEquals(1, counter.getPlayCount(1));
		assertEquals(1, counter.getPlayCount(2));
		assertEquals(4, counter.getPlayCount(3));
		assertEquals(0, counter.getPlayCount(4));
		assertEquals(0, counter.getPlayCount(5));
		assertEquals(0, counter.getPlayCount(6));
		assertEquals(2, counter.getPlayCount(7));
	}
}