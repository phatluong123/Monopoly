package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToIllinoisAve {
	private String name = "Advance to Illinois Avenue.";
	
	public void action(Player player) {
		if (player.getCurrentLocation() > 24) {
			int money = player.getMoney();
			money = money + 200;
			player.setMoney(money);
		}
		player.setCurrentLocation(24);
	}
}
