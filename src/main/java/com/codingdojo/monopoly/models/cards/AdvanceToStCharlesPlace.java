package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToStCharlesPlace extends ChanceCard {
	private String name = "Advance to St. Charles Place.";
	
	public void action(Player player) {
		if (player.getCurrentLocation() > 11) {
			int money = player.getMoney();
			money = money + 200;
			player.setMoney(money);
		}
		player.setCurrentLocation(11);
	}
}
