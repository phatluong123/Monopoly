package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class BankError extends CommunityChestCard{
	public void action(Player player) {
		player.earn(200);
	}
}
