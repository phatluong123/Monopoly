package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GetOutOfJailFreeChance extends ChanceCard {
	public GetOutOfJailFreeChance(String name) {
		super(name);

	}

	
	public void action(Player player) {
		player.setOwnsChanceJailCard(true);
	}
}
