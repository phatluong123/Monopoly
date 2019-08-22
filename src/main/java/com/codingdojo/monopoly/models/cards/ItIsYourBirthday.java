package com.codingdojo.monopoly.models.cards;

import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;

public class ItIsYourBirthday extends CommunityChestCard{
	public ItIsYourBirthday(String name) {
		super(name);
	}
	
	public ItIsYourBirthday() {
		super("It is your birthday. Collect $10 from every player.");
	}

	public void action(Player player) {
		int gift = 0;
		for(Player p: Game.getPlayers()) {
			p.pay(10);
			gift += 10;
		}
		player.earn(gift);
	}
}
