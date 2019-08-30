package com.codingdojo.monopoly.scmodels;

import com.codingdojo.monopoly.models.Player;
import com.codingdojo.monopoly.models.Space;
import com.codingdojo.monopoly.models.Street;

public class BuildMessage implements Message {
	private Player player;
	private Street street;
	public Player getPlayer() {
		return player;
	}
	public Street getStreet() {
		return street;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setStreet(Space space) {
		this.street = (Street)space;
	}
}
