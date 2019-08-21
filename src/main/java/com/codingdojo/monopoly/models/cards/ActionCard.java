package com.codingdojo.monopoly.models.cards;

public abstract class ActionCard {
	private String name;
	
	public ActionCard(String name) {
		this.name = name;
	}
	
	public void action() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}