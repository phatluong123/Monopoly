package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class MakeGeneralRepairs {
	private String name = "Make general repairs on your properties.";
	
	public void action(Player player) {
		int houses = player.getNumberOfHouses();
		int hotels = player.getNumberOfHotels();
		player.payOther((25*houses)+(100*hotels));
	}
}
