package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class BuildingLoanMatures extends ChanceCard {
	private String name = "Your building loan matures";
	
	public void action(Player player) {
		int money = player.getMoney();
		money = money + 150;
		player.setMoney(money);
	}
}
