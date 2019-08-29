package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToStCharlesPlace extends ChanceCard {
	public AdvanceToStCharlesPlace() {
		super("Advance to St. Charles Place. If you pass GO, collect $200.");
	}
	
	public AdvanceToStCharlesPlace(String name) {
		super(name);
	}

	
	public void action(Player player) {
		player.moveTo(11);
	}
}
