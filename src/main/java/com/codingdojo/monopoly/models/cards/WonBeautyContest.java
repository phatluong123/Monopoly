package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class WonBeautyContest extends CommunityChestCard {
	public WonBeautyContest(String name) {
		super(name);
	}
	
	public WonBeautyContest() {
		super("You have won second prize in a beauty contest. Collect $10");
	}

	public void action(Player player) {
		player.earn(10);
	}
}
