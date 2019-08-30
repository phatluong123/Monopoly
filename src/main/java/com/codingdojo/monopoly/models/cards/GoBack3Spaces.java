package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;

public class GoBack3Spaces extends ChanceCard {
	public GoBack3Spaces(String name) {
		super(name);
	}
	
	public GoBack3Spaces() {
		super("Go back three spaces.");
	}
	
	public void action(Player player) {
		int location = (player.getCurrentLocation() + 37) % 40;
		player.setCurrentLocation(location);
		Game.spaceAction(player);
	}
}
