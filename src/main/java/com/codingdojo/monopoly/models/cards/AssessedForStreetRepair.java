package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AssessedForStreetRepair extends CommunityChestCard {
	public AssessedForStreetRepair(String name) {
		super(name);
	}
	
	public AssessedForStreetRepair() {
		super("You are assessed for street repairs: Pay $40 per house and $115 per hotel you own");
	}

	public void action(Player player) {
		player.pay(player.getNumberOfHouses()*40+player.getNumberOfHotels()*115);
	}
}
