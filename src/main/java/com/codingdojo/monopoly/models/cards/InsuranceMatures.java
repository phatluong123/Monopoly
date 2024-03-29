package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class InsuranceMatures extends CommunityChestCard {
	public InsuranceMatures(String name) {
		super(name);
	}
	
	public InsuranceMatures() {
		super("Life insurance matures. Collect $100.");
	}

	public void action(Player player) {
		player.earn(100);
	}
}
