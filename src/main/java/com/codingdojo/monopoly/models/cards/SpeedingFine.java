package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class SpeedingFine extends ChanceCard{

	
	public SpeedingFine(String name) {
		super(name);
	}

	public void action(Player player) {
		player.payOther(15);
	}
}
//player looses 15