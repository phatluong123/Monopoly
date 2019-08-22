package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GoToJailChance extends ChanceCard {
	public GoToJailChance(String name) {
		super(name);
	}
	
	public GoToJailChance() {
		super("Go to jail. Go directly to jai, do not pass go, do not collect $200.");
	}

	public void action(Player player) {
		player.goToJail();
	}

}
