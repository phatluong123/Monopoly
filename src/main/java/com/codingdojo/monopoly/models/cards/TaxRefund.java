package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class TaxRefund extends CommunityChestCard{
	public TaxRefund(String name) {
		super(name);
	}
	
	public TaxRefund() {
		super("Income tax refund. Collect $20");
	}

	public void action(Player player) {
		player.earn(20);
	}

}
