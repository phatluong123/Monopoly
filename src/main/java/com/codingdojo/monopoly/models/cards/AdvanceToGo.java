package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToGo extends CommunityChestCard {
	public void action(Player player) {
		player.moveTo(0);
	}
}
