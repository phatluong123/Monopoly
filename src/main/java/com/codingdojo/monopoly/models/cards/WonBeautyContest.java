package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class WonBeautyContest extends CommunityChestCard {
	public void action(Player player) {
		player.Earn(10);
	}
}
