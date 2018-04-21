package com.lac.spring.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TrackCounter {

	private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();

	@Pointcut("execution(* com.lac.spring.aop.CompactDisc.play(int)) && args(trackNumber)")
	public void play(int trackNumber) {	}

	@Before("play(trackNumber)")
	public void countTrack(int trackNumber) {
		int currentCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, currentCount + 1);
	}

	public int getPlayCount(int trackNumber) {
		return trackCounts.containsKey(trackNumber) ? trackCounts
				.get(trackNumber) : 0;
	}
}
