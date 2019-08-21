package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToGo extends CommunityChestCard {
	
	public AdvanceToGo(String name) {
		super(name);
	}

	public void action(Player player) {
		player.moveTo(0);
		player.setMoney(player.getMoney()+200);
	}
}
