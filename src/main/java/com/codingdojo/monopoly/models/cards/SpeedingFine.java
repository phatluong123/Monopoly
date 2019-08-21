package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class SpeedingFine {
	private String name = "Speeding Fine";
	
	public void action(Player player) {
		player.payOther(15);
	}
}
//player looses 15