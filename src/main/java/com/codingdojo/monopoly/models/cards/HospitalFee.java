package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class HospitalFee extends CommunityChestCard{
	public void action(Player player) {
		player.payOther(100);
	}
}
