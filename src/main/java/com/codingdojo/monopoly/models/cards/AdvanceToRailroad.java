package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToRailroad extends ChanceCard {

	public AdvanceToRailroad(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void action(Player player) {
		if(player.getCurrentLocation()>35 || player.getCurrentLocation()<5) {
			player.setCurrentLocation(5);
		}
		if(player.getCurrentLocation()>5 && player.getCurrentLocation()<15) {
			player.setCurrentLocation(15);
		}
		if(player.getCurrentLocation()>15 && player.getCurrentLocation()<25) {
			player.setCurrentLocation(25);
		}
		if(player.getCurrentLocation()>25 && player.getCurrentLocation()<35) {
			player.setCurrentLocation(35);
		}
		//NEED TO ADD LOGIC FOR WHETHER OR NOT THE SPACE IS OWNED BY SOMEONE.
	}
}
//NOT FINISHED!!!!!!!!!!!!!!!!!