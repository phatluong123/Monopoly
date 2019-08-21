package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GetOutJailFreeCommunity extends CommunityChestCard{
	public GetOutJailFreeCommunity(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void action(Player player) {
		player.setOwnJailCard(true);
	}
}
