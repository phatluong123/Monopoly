package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class DoctorFee extends CommunityChestCard{
	public DoctorFee(String name) {
		super(name);
	}
	
	public DoctorFee() {
		super("Doctor's fees. Pay $50.");
	}

	public void action(Player player) {
		player.payOther(50);
	}
}
