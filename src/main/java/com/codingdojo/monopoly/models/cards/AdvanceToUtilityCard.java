package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToUtilityCard extends ChanceCard {
	public AdvanceToUtilityCard(String name) {
		super(name);
	}
	
	public void action(Player player) {
		if(player.getCurrentLocation() > 12 && player.getCurrentLocation() < 28) {
			player.setCurrentLocation(28);
		}
		else {
			player.setCurrentLocation(12);
		}
//NEED TO ADD LOGIC FOR CHECKING WHETHER OR NOT THE UTILITY IS OWNED.
	}
}