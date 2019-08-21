package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class BankPaysYou extends ChanceCard {
	private String name = "Bank pays you";
	
	public void action(Player player) {
		int money = player.getMoney();
		money = money + 50;
		player.setMoney(money);
		System.out.println("Bank pays you a dividend of $50");
	}
}
