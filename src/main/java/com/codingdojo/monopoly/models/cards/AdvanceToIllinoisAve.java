package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToIllinoisAve {
	private String name = "Advance to Illinois Avenue.";
	
	public void action(Player player) {
		player.moveTo(24);
	}
}
