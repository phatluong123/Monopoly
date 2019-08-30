package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;

public class AdvanceToBoardwalk extends ChanceCard {
	public AdvanceToBoardwalk(String name) {
		super(name);
	}
	
	public AdvanceToBoardwalk() {
		super("Advance to Boardwalk.");
	}

	
	public void action(Player player) {
		player.moveTo(39);
		if(Game.isSpaceOwned(player.getCurrentLocation())) {
			player.payRent();
		}
	}
}
