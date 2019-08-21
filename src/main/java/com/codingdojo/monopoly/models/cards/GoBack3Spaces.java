package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GoBack3Spaces extends ChanceCard {
	public GoBack3Spaces(String name) {
		super(name);
	}
	
	public void action(Player player) {
		int location = player.getCurrentLocation();
		location = location - 3;
		player.setCurrentLocation(location);
	}
}
