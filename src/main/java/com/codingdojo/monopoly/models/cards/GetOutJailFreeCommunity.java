package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Player;

public class GetOutJailFreeCommunity extends CommunityChestCard{
	public GetOutJailFreeCommunity(String name) {
		super(name);
	}
	
	public GetOutJailFreeCommunity() {
		super("Get out of jail free!");
	}

	public void action(Player player) {
		player.setOwnsChestJailCard(true);
	}
}
