package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GoToJail extends CommunityChestCard{
	public void action(Player player) {
		player.goToJail();
	}
}
