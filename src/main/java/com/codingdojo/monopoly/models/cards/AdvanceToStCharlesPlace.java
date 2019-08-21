package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToStCharlesPlace extends ChanceCard {
	public AdvanceToStCharlesPlace(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	public void action(Player player) {
		player.moveTo(11);
	}
}
