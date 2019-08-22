package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class HospitalFee extends CommunityChestCard{
	public HospitalFee(String name) {
		super(name);
	}
	
	public HospitalFee() {
		super("Hospital fees. Pay $100.");
	}

	public void action(Player player) {
		player.pay(100);
	}
}
