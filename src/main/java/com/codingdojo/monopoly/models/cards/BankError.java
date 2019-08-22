package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class BankError extends CommunityChestCard{
	public BankError(String name) {
		super(name);
	}
	
	public BankError() {
		super("Bank error in your favor. Collect $200.");
	}

	public void action(Player player) {
		player.earn(200);
	}
}
