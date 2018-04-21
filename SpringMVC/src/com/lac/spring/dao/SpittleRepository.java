package com.lac.spring.dao;

import java.util.List;

import com.lac.spring.po.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count);
	Spittle findOne(long id);
	Spittle save(Spittle spittle);
}
