package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class Inherit extends CommunityChestCard{
	public Inherit(String name) {
		super(name);
	}
	
	public Inherit() {
		super("You inherit $100.");
	}

	public void action(Player player) {
		player.earn(100);
	}
}
