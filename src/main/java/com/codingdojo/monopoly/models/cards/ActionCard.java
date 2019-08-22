package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public abstract class ActionCard {
	private String name;
	
	public ActionCard(String name) {
		this.name = name;
	}
	
	public abstract void action();
	
	public abstract void action(Player player);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}