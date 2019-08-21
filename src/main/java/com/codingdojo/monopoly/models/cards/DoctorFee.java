package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class DoctorFee extends CommunityChestCard{
	
	public DoctorFee(String name) {
		super(name);
	}

	public void action(Player player) {
		player.payOther(50);
	}
}
