package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GetOutJailFreeCommunity extends CommunityChestCard{
	public void action(Player player) {
		player.setOwnJailCard(true);
	}
}
