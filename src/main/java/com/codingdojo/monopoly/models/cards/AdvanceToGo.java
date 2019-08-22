package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToGo extends CommunityChestCard {	
	public AdvanceToGo(String name) {
		super(name);
	}
	
	public AdvanceToGo() {
		super("Advance to go. Collect $200.");
	}

	public void action(Player player) {
		player.moveTo(0);
	}
}
