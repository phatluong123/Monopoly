package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToIllinoisAve extends ChanceCard{
	public AdvanceToIllinoisAve(String name) {
		super(name);
	}

	private String name = "Advance to Illinois Avenue.";
	
	public void action(Player player) {
		player.moveTo(24);
	}
}
