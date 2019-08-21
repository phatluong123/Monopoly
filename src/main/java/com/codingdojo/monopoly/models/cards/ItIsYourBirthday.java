package com.codingdojo.monopoly.models.cards;

import java.util.List;
import com.codingdojo.monopoly.models.Game;
import com.codingdojo.monopoly.models.Player;

public class ItIsYourBirthday extends CommunityChestCard{
	
	// Temporary List of players
	public void action(Player player) {
		List<Player> allPlayers = Game.getAllPlayers();
		for (int i=0; i<allPlayers.size();i++ ) {
			allPlayers.get(i).payOther(10);	
		}
		player.Earn(allPlayers.size()*10);
	}
}
