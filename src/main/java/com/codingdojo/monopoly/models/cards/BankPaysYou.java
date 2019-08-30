package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class BankPaysYou extends ChanceCard {
	public BankPaysYou(String name) {
		super(name);
	}
	
	public BankPaysYou() {
		super("Bank pays you dividend of $50.");
	}
	
	public void action(Player player) {
		player.earn(50);
	}
}
