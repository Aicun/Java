package com.lac.interceptingfilter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {

	private List<Filter> filterChain = new ArrayList<Filter>();
	private Target target;

	public void addFilter(Filter filter) {
		filterChain.add(filter);
	}

	public void execute(String request) {
		for (Filter filter : filterChain) {
			filter.execute(request);
		}
		target.execute(request);
	}

	public void setTarget(Target target) {
		this.target = target;
	}
}
