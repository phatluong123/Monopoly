package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GoToJail extends CommunityChestCard{
	public GoToJail(String name) {
		super(name);
	}

	public void action(Player player) {
		player.goToJail();
	}
}
