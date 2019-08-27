package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class BuildingLoanMatures extends ChanceCard {
	public BuildingLoanMatures(String name) {
		super(name);
	}
	
	public BuildingLoanMatures() {
		super("Your building loan matures. Collect $150");
	}
	
	public void action(Player player) {
		player.earn(150);
	}
}
