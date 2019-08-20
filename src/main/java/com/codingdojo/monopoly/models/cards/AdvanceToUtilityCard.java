package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToUtilityCard extends ChanceCard {
	private String name = "Advance to nearest utility";
	
	@Override
	public void action(Player player) {
		if(player.getCurrentLocation() > 12 && player.getCurrentLocation() < 28) {
			player.setCurrentLocation(28);
		}
		else {
			player.setCurrentLocation(12);
		}
		if ()
	}
}