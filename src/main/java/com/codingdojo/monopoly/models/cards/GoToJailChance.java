package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GoToJailChance extends ChanceCard {
	
	public GoToJailChance(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void action(Player player) {
		player.setInJail(true);
		player.setCurrentLocation(10);
	}

}
