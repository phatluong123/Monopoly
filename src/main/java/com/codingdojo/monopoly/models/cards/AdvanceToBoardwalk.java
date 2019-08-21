package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToBoardwalk extends ChanceCard {
	private String name = "Advance to boardwalk";
	
	public void action(Player player) {
		player.setCurrentLocation(39);
	}
}
