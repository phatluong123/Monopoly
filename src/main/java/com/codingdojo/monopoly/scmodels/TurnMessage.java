package com.codingdojo.monopoly.scmodels;

public class TurnMessage implements Message {
	private boolean myTurn;
	public boolean isMyTurn() {
		return myTurn;
	}
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
}
