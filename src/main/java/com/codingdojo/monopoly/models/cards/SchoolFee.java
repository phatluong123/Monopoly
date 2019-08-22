package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class SchoolFee extends CommunityChestCard{
	public SchoolFee(String name) {
		super(name);
	}
	
	public SchoolFee() {
		super("School fees. Pay $50");
	}

	public void action(Player player) {
		player.pay(50);
	
	}
}