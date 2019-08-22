package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class AdvanceToIllinoisAve extends ChanceCard{
	public AdvanceToIllinoisAve(String name) {
		super(name);
	}
	
	public AdvanceToIllinoisAve() {
		super("Advance to Illinois Avenue. If you pass go, collect $200");
	}
	
	public void action(Player player) {
		player.moveTo(24);
	}
}
