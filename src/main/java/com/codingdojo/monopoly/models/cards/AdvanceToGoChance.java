package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToGoChance extends ChanceCard {
	public AdvanceToGoChance(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void action(Player player) {
		player.moveTo(0);
	}
}
