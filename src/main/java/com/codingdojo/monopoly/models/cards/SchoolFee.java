package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class SchoolFee extends CommunityChestCard{
	public void action(Player player) {
		player.payOther(50);
	
	}
}