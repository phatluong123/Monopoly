package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GoToJailChance extends ChanceCard {
	private String name = "Go to jail.";
	
	public void action(Player player) {
		player.setInJail(true);
		player.setCurrentLocation(10);
	}

}
