package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class DoctorFee extends CommunityChestCard{
	
	public void action(Player player) {
		player.payOther(50);
	}
}
