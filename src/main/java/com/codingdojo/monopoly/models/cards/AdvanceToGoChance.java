package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToGoChance extends ChanceCard {
	private String name = "Advance to go";
	
	public void action(Player player) {
		player.moveTo(0);
	}
}
