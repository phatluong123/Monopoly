package com.codingdojo.monopoly.models.cards;

public class AdvanceToUtilityCard extends ChanceCard {
	private String name = "Advance to nearest utility";
	
	@Override
	public void action(Player player) {
		if(player.currentPosition > 12 && player.currentPostion < 28) {
			player.setCurrentPosition(28);
		}
		else {
			player.setCurrentPostion(12);
		}
		
	}
}
//test