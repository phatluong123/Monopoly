package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToReadingRailroad extends ChanceCard {
	public AdvanceToReadingRailroad(String name) {
		super(name);
	}
	
	public AdvanceToReadingRailroad() {
		super("Take a trip to Reading Railroad. If you pass go, collect $200.");
	}
	
	public void action(Player player) {
		player.moveTo(5);
	}
}
