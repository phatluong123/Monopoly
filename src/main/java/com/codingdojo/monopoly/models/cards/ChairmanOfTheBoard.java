package com.codingdojo.monopoly.models.cards;

import java.util.List;

import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;

public class ChairmanOfTheBoard extends ChanceCard {
	public ChairmanOfTheBoard() {
		super("You have been elected chairman of the board.  Pay each player $50.");
	}
	
	public ChairmanOfTheBoard(String name) {
		super(name);
	
	}
	public void action(Player player) {
		List<Player> allPlayers = Game.getPlayers();
		player.addDebt(allPlayers.size()*50);
		if (player.getMoney() >= player.getDebt()) {
		for (int i=0; i<allPlayers.size();i++ ) {
			allPlayers.get(i).earn(50);	
		}
		player.pay(allPlayers.size()*50);
		}
	}
}
