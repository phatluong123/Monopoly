package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToUtilityCard extends ChanceCard {
	public AdvanceToUtilityCard(String name) {
		super(name);
	}
	
	public AdvanceToUtilityCard() {
		super("Advance to the nearest utility.\n\nIf UNOWNED, you may buy it from the bank.\n\nIf OWNED, throw dice and pay owner ten times the amount thrown.");
	}
	
	public void action(Player player) {
		if(player.getCurrentLocation() > 12 && player.getCurrentLocation() < 28) {
			player.moveTo(28);
		}
		else {
			player.moveTo(12);
		}
		//TODO NEED TO ADD LOGIC FOR CHECKING WHETHER OR NOT THE UTILITY IS OWNED.
	}
}