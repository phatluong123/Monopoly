package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToReadingRailroad extends ChanceCard {
	private String name = "Advance to Reading Railroad";
	
	public void action(Player player) {
		player.moveTo(5);
	}
}
