package com.codingdojo.monopoly.scmodels;

public class ActionMessage implements Message {
	private int playerIndex;
	private String action;
	private int targetIndex;
	public int getPlayerIndex() {
		return playerIndex;
	}
	public String getAction() {
		return action;
	}
	public int getTargetIndex() {
		return targetIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}
}
