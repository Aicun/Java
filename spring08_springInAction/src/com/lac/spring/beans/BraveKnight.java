package com.lac.spring.beans;

public class BraveKnight implements Knight {
	private Quest quest;
	private String name;

	public BraveKnight(Quest quest,String name) {
		this.quest = quest;
		this.name = name;
	}

	public void embarkOnQuest() {
		quest.embark();
	}

	@Override
	public String getName() {
		return name;
	}
}
