package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class SpeedingFine extends ChanceCard{
	public SpeedingFine(String name) {
		super(name);
	}
	
	public SpeedingFine() {
		super("Speeding fine. Pay $15.");
	}

	public void action(Player player) {
		player.payOther(15);
	}
}
//player looses 15