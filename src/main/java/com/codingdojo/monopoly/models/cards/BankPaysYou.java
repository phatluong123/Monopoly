package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class BankPaysYou extends ChanceCard {
	public BankPaysYou(String name) {
		super(name);
	}
	
	public void action(Player player) {
		int money = player.getMoney();
		money = money + 50;
		player.setMoney(money);
		System.out.println("Bank pays you a dividend of $50");
	}
}
