package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToGo extends CommunityChestCard {
	public void action(Player player) {
		player.earn(200);
		player.setCurrentLocation(0);
	}
}
