package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToBoardwalk extends ChanceCard {
	public AdvanceToBoardwalk(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	public void action(Player player) {
		player.moveTo(39);
	}
}
