package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class SaleStock extends CommunityChestCard{
	public SaleStock(String name) {
		super(name);
	}

	public void action(Player player) {
		player.earn(50);
	}
}
