package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToReadingRailroad extends ChanceCard {
	private String name = "Advance to Reading Railroad";
	
	public void action(Player player) {
		if(player.getCurrentLocation() > 5) {
			int money = player.getMoney();
			money = money + 200;
			player.setMoney(money);
		}
		player.setCurrentLocation(5);
	}
}
