package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class MakeGeneralRepairs extends ChanceCard {

	
	public MakeGeneralRepairs(String name) {
		super(name);
	}

	public void action(Player player) {
		int houses = player.getNumberOfHouses();
		int hotels = player.getNumberOfHotels();
		player.addDebt((25*houses)+(100*hotels));
		if (player.getMoney() >= player.getDebt()) {
			player.pay((25*houses)+(100*hotels));
		}
	}
}
