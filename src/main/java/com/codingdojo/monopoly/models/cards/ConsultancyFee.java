package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class ConsultancyFee extends CommunityChestCard{
	public ConsultancyFee(String name) {
		super(name);
	}
	
	public ConsultancyFee() {
		super("Receive $25 consultancy fee.");
	}

	public void action(Player player) {
		player.earn(25);
	}

}
