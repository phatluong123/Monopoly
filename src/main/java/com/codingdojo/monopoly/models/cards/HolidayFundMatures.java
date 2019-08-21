package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class HolidayFundMatures extends CommunityChestCard {
	public HolidayFundMatures(String name) {
		super(name);
	}

	public void action(Player player) {
		player.earn(100);
	}
}
