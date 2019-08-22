package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToRailroad extends ChanceCard {
	public AdvanceToRailroad(String name) {
		super(name);
	}
	
	public AdvanceToRailroad() {
		super("Advance to the nearest railroad.\n\nIf UNOWNED, you may buy it from the Bank.\n\nIf OWNED, pay owner twice the rental to which they are otherwise entitled.");
	}

	public void action(Player player) {
		if(player.getCurrentLocation()>35 || player.getCurrentLocation()<5) {
			player.moveTo(5);
		}
		if(player.getCurrentLocation()>5 && player.getCurrentLocation()<15) {
			player.moveTo(15);
		}
		if(player.getCurrentLocation()>15 && player.getCurrentLocation()<25) {
			player.moveTo(25);
		}
		if(player.getCurrentLocation()>25 && player.getCurrentLocation()<35) {
			player.moveTo(35);
		}
		//TODO NEED TO ADD LOGIC FOR WHETHER OR NOT THE SPACE IS OWNED BY SOMEONE.
	}
}
//TODO NOT FINISHED!!!!!!!!!!!!!!!!!