package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class ConsultancyFee extends CommunityChestCard{
	public ConsultancyFee(String name) {
		super(name);
	}

	public void action(Player player) {
		player.earn(25);
	}

}
