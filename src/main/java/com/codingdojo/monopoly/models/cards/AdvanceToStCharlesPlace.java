package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToStCharlesPlace extends ChanceCard {
	private String name = "Advance to St. Charles Place.";
	
	public void action(Player player) {
		player.moveTo(11);
	}
}
