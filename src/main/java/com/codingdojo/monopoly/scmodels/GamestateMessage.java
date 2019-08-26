package com.codingdojo.monopoly.scmodels;

public class GamestateMessage implements Message {
	private String gamestate;

	public String getGamestate() {
		return gamestate;
	}

	public void setGamestate(String gamestate) {
		this.gamestate = gamestate;
	}
}
