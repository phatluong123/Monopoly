package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AssessedForStreetRepair extends CommunityChestCard {
	public AssessedForStreetRepair(String name) {
		super(name);

	}

	public void action(Player player) {
		player.payOther(player.getNumberOfHouses()*40+player.getNumberOfHotels()*115);
	}
}
