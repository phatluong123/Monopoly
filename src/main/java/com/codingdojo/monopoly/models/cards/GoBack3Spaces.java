package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GoBack3Spaces extends ChanceCard {
	private String name = "Go back 3 spaces.";
	
	public void action(Player player) {
		int location = player.getCurrentLocation();
		location = location - 3;
		player.setCurrentLocation(location);
	}
}
